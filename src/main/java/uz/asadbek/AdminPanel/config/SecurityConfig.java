package uz.asadbek.AdminPanel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.asadbek.AdminPanel.service.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final UserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig( UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(configurer ->
						configurer
								.requestMatchers("/admin").hasRole("ADMIN")
								.requestMatchers("/auth/login", "/auth/register", "/error")
								.permitAll()

								.anyRequest().hasAnyRole("ADMIN", "USER")
				)
				.formLogin(form ->
						form
								.loginPage("/auth/login")
								.usernameParameter("email")
								.passwordParameter("password")
								.loginProcessingUrl("/process_login")
								.defaultSuccessUrl("/home", true)
								.failureUrl("/auth/login?error")
								.permitAll()
				)
				.logout(logout ->
						logout
								.logoutUrl("/logout")
								.logoutSuccessUrl("/auth/login"));
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
				httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}


}