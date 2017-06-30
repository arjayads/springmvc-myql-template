package tsi.map.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tsi.map.model.Login;
import tsi.map.repo.LoginRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    @Autowired
    DataSource dataSource;

    @Autowired
    private LoginRepo loginRepo;

    /**
     *
     * @param builder
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity builder) throws Exception {
        builder .ignoring()
                .antMatchers("/resources/**", "/logoutSuccess");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login**", "/api/**", "/index","/").permitAll()
        .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .successHandler(new CustomAuthenticationSuccessHandler(loginRepo, env))
                .failureHandler(new CustomAuthenticationFailureHandler(loginRepo))
                .permitAll()
        .and()
                .logout()
                .logoutSuccessUrl("/login?logout").permitAll()
        .and()
                .csrf()
        .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password, enabled FROM User WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, Role.name AS role FROM UserRole " +
                                            "JOIN User on FK_userId = User.id " +
                                            "JOIN Role on FK_roleId = Role.id " +
                                            "WHERE username=?");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}


class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private LoginRepo loginRepo;

    private RedirectStrategy redirectStrategy;

    Environment env;

    public CustomAuthenticationSuccessHandler() {
        redirectStrategy = new DefaultRedirectStrategy();
    }

    public CustomAuthenticationSuccessHandler(LoginRepo loginRepo, Environment env) {
        this();
        this.loginRepo = loginRepo;
        this.env = env;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // log user login
        Login login = new Login();
        login.setUsername(authentication.getName());
        login.setIp(request.getRemoteAddr());
        login.setSuccessful(true);

        loginRepo.save(login);

        String targetUrl = request.getParameter("target");

        if (targetUrl == null || targetUrl.trim().equals("")) {
            targetUrl = "/";
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}


class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    private LoginRepo loginRepo;

    public CustomAuthenticationFailureHandler() {
        super();
    }

    public CustomAuthenticationFailureHandler(LoginRepo loginRepo) {
        this();
        this.loginRepo = loginRepo;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Login login = new Login();

        login.setUsername(request.getParameter("username"));
        login.setIp(request.getRemoteAddr());
        login.setSuccessful(false);
        login.setMaster(false);

        loginRepo.save(login);

        response.sendRedirect("/login?error");
    }
}