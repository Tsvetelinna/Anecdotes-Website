package course.spring.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {
    private String text;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Anecdote anecdote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();

    public Comment(String text, Object o, Object o1) {
    }

    public Comment(String text) {
        this.text = text;
    }

    public CommentInfo toCommentInfo() {
        return new CommentInfo(getId(), getText(), getAuthor().getName(), getAnecdote().getId(), getCreatedAt());
    }

    public static Comment fromCommentInfo(CommentInfo info) {
        return new Comment(info.getDescription());
    }
}
