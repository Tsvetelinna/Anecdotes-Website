package course.spring.config;

import course.spring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static course.spring.entity.Role.ADMIN;
import static course.spring.entity.Role.USER;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(POST, "/login", "/register").permitAll()
                .antMatchers("/home-admin", "/categories-admin", "add-category", "update-categories", "/anecdotes-admin", "/users", "/profile-admin").hasAnyRole(ADMIN.toString())
                .antMatchers("/home", "/categories-user", "categories-user", "add-anecdote", "/anecdotes-user", "/my-anecdotes", "/profile-user").hasAnyRole(USER.toString())
                .antMatchers("/").permitAll()
                .and().formLogin()
                .successHandler(myAuthenticationSuccessHandler());

    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::getUserByUsername;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

}
