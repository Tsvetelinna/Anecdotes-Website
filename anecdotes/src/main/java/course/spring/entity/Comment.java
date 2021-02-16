package course.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
}
