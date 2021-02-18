package course.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnecdoteInfo {
    private Long id;
    private String description;
    private String picture;
    private Long categoryId;
}
