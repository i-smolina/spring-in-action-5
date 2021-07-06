package ru.smolina.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

//	@Autowired
//	DataSource dataSource;

	// in-memory-authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user").password("$2y$12$meABCWna7qjiJr71PqUEkOsIejdbEKSzvivpHOO7D35FTHEhko9pS").authorities("ROLE_USER")
//		.and()
//		.withUser("admin").password("$2y$12$meABCWna7qjiJr71PqUEkOsIejdbEKSzvivpHOO7D35FTHEhko9pS").authorities("ROLE_ADMIN");
//	}

	// jdbc-authentication
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			
//			auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.withDefaultSchema()
//			.withUser("user").password("$2y$12$byJlDa8Nz0u5K18pepFvaeWzIxaVD7GgvuUQQ1FfuQLvEkjhhaNJ2")
//			.authorities("ROLE_USER");
//		}

//		
//
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/design/**", "/orders/**").authenticated()
				.antMatchers("/", "/h2-console/**").permitAll()
				.and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/design", true)
				.and().logout().logoutSuccessUrl("/");
		//http.csrf().disable();
		//http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
