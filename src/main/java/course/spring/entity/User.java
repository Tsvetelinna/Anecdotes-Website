package course.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profilePicture;
    private AccountStatus status = AccountStatus.ACTIVE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
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
        return status.equals(AccountStatus.ACTIVE);
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status.equals(AccountStatus.ACTIVE);
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(AccountStatus.ACTIVE);
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status.equals(AccountStatus.ACTIVE);
    }
}
