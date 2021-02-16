package course.spring.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Anecdote> anecdotes = new ArrayList<>();

    public Category(@NotNull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return name.equals(category.name) &&
                Objects.equals(anecdotes, category.anecdotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, anecdotes);
    }
}
