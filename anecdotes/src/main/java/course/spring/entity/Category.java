package course.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Anecdote> anecdotes = new ArrayList<>();
}
