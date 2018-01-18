package ljx.ashin.bean;

import java.io.Serializable;
import java.util.Date;

public class TBlogContent implements Serializable {
    private Integer id;

    private String title;

    private Date edittime;

    private String author;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "TBlogContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", edittime=" + edittime +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}