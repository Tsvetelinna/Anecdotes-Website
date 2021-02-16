package course.spring.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anecdotes")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Anecdote extends BaseEntity {

    private String text;
    @Lob
    private byte[] picture;
    @NotNull
    @ManyToOne
    private User author;
    @NotNull
    @ManyToOne
    private Category category;
}
