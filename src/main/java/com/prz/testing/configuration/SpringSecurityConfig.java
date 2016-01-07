package com.prz.testing.configuration;

import com.prz.testing.enumerate.RoleName;
import com.prz.testing.security.UrlAuthenticationHandler;
import com.prz.testing.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    private UserDetailsService userDetailsService;

   /* public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl())
                .passwordEncoder(new ShaPasswordEncoder());

    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                //.antMatchers("/login.html").permitAll()
                .antMatchers("/rest/student").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login").permitAll()
                .successHandler(handler).failureUrl("/error")
                .and()
                .logout().permitAll();

//        http
//                .authorizeRequests()
//                //.antMatchers("/rest").hasRole("ADMIN")
//                // .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").permitAll()
//                .defaultSuccessUrl("/main").failureUrl("/login").successHandler(handler)
//                .and().exceptionHandling().accessDeniedPage("/login")
//                .and().logout().permitAll();//.and().invalidateHttpSession(true).logoutSuccessUrl("/");

        //http.anonymous().authorities("USER_ANONYMOUS").principal("guest").key("foobar");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth//.userDetailsService(userDetailsService);
                .inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("student")
                .password("student")
                .roles("STUDENT");
    }

//    @Override
//    public void configure(WebSecurity web) {
//
//        web.ignoring().antMatchers("/app/**");
//    }
////
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean(name = "userDetailsService")
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean(name = "authenticationProvider")
//    public AuthenticationProvider authenticationProvider() {
//        return new UserDetailsServiceImpl();
//    }
//
//
//    @Bean(name = "passwordEncoder")
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
