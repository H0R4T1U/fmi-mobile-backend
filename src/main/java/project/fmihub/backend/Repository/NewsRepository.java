package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitleAndLanguage(String title, String language);
    List<News> findAllByLanguageOrderByDateDesc(String language);
}
