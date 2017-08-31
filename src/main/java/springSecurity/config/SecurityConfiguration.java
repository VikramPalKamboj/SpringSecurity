package springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import springSecurity.repositroy.Repositroy;
import springSecurity.service.CustomUserDetailsService;


@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses=Repositroy.class)
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{	
		
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder(){

			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword,
					String encodedPassword) {
				// TODO Auto-generated method stub
				return true;
			}
			
		});
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().
		antMatchers("**/secured/**")
		.authenticated()
		.anyRequest()
		.permitAll()
		.and().formLogin().permitAll();
	}

	
	 

}
