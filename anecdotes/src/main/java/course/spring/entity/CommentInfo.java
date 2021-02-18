package course.spring.entity;

import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
public class CommentInfo {
    private Long id;
    private String description;
    private String author;
    private Long anecdoteId;
    private Date createdAt;

    public CommentInfo(Long id, String text, String author, Long anecdoteId, Date createdAt) {
        this.id = id;
        this.description = text;
        this.author = author;
        this.createdAt = createdAt;
        this.anecdoteId = anecdoteId;
    }
}
