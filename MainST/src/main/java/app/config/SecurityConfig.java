package app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import core.user.User;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MainStAuthenticationSuccessHandler mainStAuthenticationSuccessHandler;
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) {
		try {
			auth.jdbcAuthentication().dataSource(dataSource)
					.passwordEncoder(passwordEncoder())
					.usersByUsernameQuery("select login,password, enabled from st_users where login=?")
					.authoritiesByUsernameQuery("select login, role from st_user_details where login=?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/index", "/css/**", "/core/**", "/js/**", "/image/**").permitAll()
			.antMatchers("/admin/**").access("hasRole('" + User.Role.ADMIN + "')")
			.antMatchers("/student/**").access("hasRole('" + User.Role.STUDENT + "')")
			.antMatchers("/teacher/**").access("hasRole('" + User.Role.TEACHER + "')")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
			.successHandler(mainStAuthenticationSuccessHandler)
			.and()
			.logout().permitAll();

		http.exceptionHandling().accessDeniedPage("/failure");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}