package adam.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "posts")

public class PostDTO implements Serializable {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostDTO(String tittle, Date postDate, String content) {
        this.tittle = tittle;
        this.postDate = postDate;
        this.content = content;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "Id_post")
    private int id;

    @Column(name = "Tittle", columnDefinition = "VARCHAR(90) NOT NULL")
    private String tittle;

    @Column(name = "Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @Column(name = "Content", length = 65535,columnDefinition="Text")
    private String content;

    public PostDTO() {
    }
    }
