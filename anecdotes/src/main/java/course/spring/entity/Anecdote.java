package course.spring.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anecdotes")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Anecdote extends BaseEntity {

    private String text;
    private String picture;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();

    @OneToMany(mappedBy = "anecdote", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Anecdote(String text, String picture, @NotNull User author, @NotNull Category category) {
        this.text = text;

        if (picture == null || picture.isEmpty() || picture.isBlank()) {
            this.picture = null;
        } else {
            this.picture = picture;
        }
        this.author = author;
        this.category = category;
    }
}
