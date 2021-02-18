package course.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements UserDetails {

    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profilePicture;
    private boolean active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Anecdote> anecdotes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public User(@NotNull String name, @NotNull String username, @NotNull String password, Role role, String profilePicture) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return active == user.active &&
                name.equals(user.name) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                role == user.role &&
                Objects.equals(profilePicture, user.profilePicture) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt) &&
                Objects.equals(anecdotes, user.anecdotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, username, password, role, profilePicture, active, createdAt, updatedAt, anecdotes);
    }
}
