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
    private String picture;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
