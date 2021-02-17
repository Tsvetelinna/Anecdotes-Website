package course.spring.entity;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnecdoteInfo  {
    private Long id;
    private String description;
    private byte[] picture;
    private Long categoryId;
}
