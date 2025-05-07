package project.fmihub.backend.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.News;
import project.fmihub.backend.Repository.NewsRepository;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Autowired
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Scheduled(fixedRate = 86400000) // Run every 24 hours
    @Transactional
    public void fetchAndStoreNews(){
        System.out.println("Fetching new news from multiple RSS Feeds");
        Map<String, String> rssFeeds = Map.of(
                "ro", "https://www.cs.ubbcluj.ro/feed/",
                "hu", "https://www.cs.ubbcluj.ro/hu/feed/",
                "de", "https://www.cs.ubbcluj.ro/de/feed/"
        );

        for (Map.Entry<String, String> entry : rssFeeds.entrySet()) {
            fetchNewsFromFeed(entry.getValue(), entry.getKey());
        }

    }

    private void fetchNewsFromFeed(String rssUrl, String language){
        try{
            URL url = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));

            List<SyndEntry> entries = feed.getEntries();
            int count = Math.min(entries.size(), 20);
            System.out.println("Total entries fetched " + entries.size() + " articles for language:"+language);
            //entries.forEach(System.out::println);
            int addedcount = 0;
            for(int i = 0; i < count; i++){
                SyndEntry entry = entries.get(i);
                if (!newsRepository.existsByTitleAndLanguage(entry.getTitle(), language)){
                    News article = new News(entry.getTitle(), entry.getPublishedDate(), entry.getLink(), language);
                    System.out.println(article);
                    newsRepository.save(article);
                    addedcount++;
                }
            }
            if(addedcount > 0){
                removeOldArticle(language);
            }
            System.out.println("Fetched and stored " + count + " new news for language:"+language);
        } catch(Exception e){
            System.out.println("Error fetching RSS feed: " + e.getMessage());
        }
    }

    private void removeOldArticle(String language){
        List<News> articles = newsRepository.findAllByLanguageOrderByDateAsc(language);
        int excessCount = articles.size() - 20;
        if(excessCount > 0){
            List<News> toDelete = articles.subList(0, excessCount);
            newsRepository.deleteAll(toDelete);
            System.out.println("Deleted " + toDelete.size() + " new articles");
        }
    }

    public List<News> getAllNewsByLanguage(String language) {
        return newsRepository.findAllByLanguageOrderByDateDesc(language);
    }

    public Collection<News> getAllNews() {
        return newsRepository.findAll();

    }
}
