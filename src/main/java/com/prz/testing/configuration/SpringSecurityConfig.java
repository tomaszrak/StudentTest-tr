package com.prz.testing.configuration;

import com.prz.testing.enumerate.RoleName;
import com.prz.testing.security.UrlAuthenticationHandler;
import com.prz.testing.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ROLO on 06.11.2015.
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = "com.prz.testing")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlAuthenticationHandler handler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().antMatchers("/main").hasRole("ADMIN");
        http.formLogin().loginPage("/").successHandler(handler);
        http.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
        http.anonymous().authorities("USER_ANONYMOUS").principal("guest").key("foobar");
    }

    @Override
    public void configure(WebSecurity web) {

        web.ignoring().antMatchers("/app/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean(name = "authenticationProvider")
    public AuthenticationProvider authenticationProvider(){
        return new UrlAuthenticationHandler();
    }
}
