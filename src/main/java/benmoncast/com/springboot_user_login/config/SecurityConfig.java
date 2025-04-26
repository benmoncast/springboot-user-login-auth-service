package benmoncast.com.springboot_user_login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import benmoncast.com.springboot_user_login.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	 @Autowired
	    private UserService userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	   
	    
	    @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	      http
	          .authorizeHttpRequests(auth -> auth
	              .requestMatchers("/admin/**").hasRole("ADMIN")
	              .requestMatchers("/user/**").hasRole("USER")
	              .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
	              .anyRequest().authenticated()
	          )
	         
	          .formLogin(form -> form
	              .loginPage("/login")
	              .defaultSuccessUrl("/home", true)
	              .permitAll()
	          )
	          .logout(logout -> logout
	              .logoutSuccessUrl("/login?logout")
	              .invalidateHttpSession(true)
	              .deleteCookies("JSESSIONID")
	              .permitAll()
	          );

	      return http.build();
	  }

	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;
	    }
	}