package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <code>Tag</code> class is a domain model class that contains the information of a single Tag entry
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
public class Tag extends DateAudit {

    private Long id;
    private String name;
    private Set<News> news = new HashSet<>();

    public Tag() {}

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name) &&
                Objects.equals(news, tag.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, news);
    }
}
