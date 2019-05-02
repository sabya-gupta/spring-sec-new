package my.spring.sec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;
	
	//IN MEMORY
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//in memory
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//		.withUser(users.username("user1").password("Password123").roles("EMPLOYEE"))
//		.withUser(users.username("user2").password("Test123").roles("EMPLOYEE", "MANAGER"))
//		.withUser(users.username("user3").password("Password123").roles("ADMIN", "EMPLOYEE"));
		
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	
	//BD Related

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.anyRequest().authenticated()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
				.formLogin()
					.loginPage("/show-login")
					.loginProcessingUrl("/do-User-Authentication")
				.permitAll()
			.and()
				.logout()
				.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/access-denied")
			;
	}
	

}
