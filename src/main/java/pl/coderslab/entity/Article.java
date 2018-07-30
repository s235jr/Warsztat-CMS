package pl.coderslab.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validator.OwnValid;
import pl.coderslab.validator.ValidationArticle;
import pl.coderslab.validator.ValidationDraft;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 200)
    @Size(max = 200, groups = {ValidationDraft.class, ValidationArticle.class})
    @OwnValid(groups = {ValidationDraft.class, ValidationArticle.class})
    private String title;
    @ManyToOne
    private Author author;
    @ManyToMany(fetch = FetchType.LAZY)
    @NotEmpty(groups = {ValidationArticle.class})
    private List<Category> categories;
    @Size(min = 100, groups = {ValidationDraft.class, ValidationArticle.class})
    @Type(type="text")
    private String content;
    private boolean draft = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private Timestamp created;
    private Timestamp updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author=" + author +
                ", categories=" + categories +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
