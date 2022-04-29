package ma.enset.patient1_mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{  //injection de dependence
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //les users qui v ont etre creer il vont etre stocker en memoire
        PasswordEncoder passwordEncoder = passwordEncoder();
         /*
        String encodedPWD = passwordEncoder.encode("1234");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1111")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("2345")).roles("USER","ADMIN");

*/

        //etablic une connexion vers une base de donnée
         auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username=?")
        .rolePrefix("ROLE_")
        .passwordEncoder(passwordEncoder);
     }
//presisier les droits d'acces
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin();
       http.authorizeRequests().antMatchers("/").permitAll();
       http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
       http.authorizeRequests().antMatchers("/user/**").hasRole("USER");

       http.authorizeRequests().anyRequest().authenticated();
       http.exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
