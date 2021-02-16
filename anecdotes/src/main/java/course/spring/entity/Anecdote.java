package course.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
