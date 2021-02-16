package course.spring.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
