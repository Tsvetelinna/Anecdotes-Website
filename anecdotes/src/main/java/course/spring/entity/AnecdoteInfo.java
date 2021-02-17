package course.spring.entity;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnecdoteInfo  {
    private String description;
    private byte[] picture;
    private Long categoryId;
}
