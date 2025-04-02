package project.fmihub.backend.Domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="news")
public class News {
    private @Id
    @GeneratedValue long id;
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String location;
    private String language;

    public News(){

    }
    public News(String title, Date date, String location, String language) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.language = language;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && Objects.equals(title, news.title) && Objects.equals(date, news.date) && Objects.equals(location, news.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, location);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }
}
