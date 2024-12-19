package com.gkefas.trackmanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security configuration class that manages access control for the application.
 * <p>
 * This configuration class restricts access to endpoints under "/api/**" based on
 * the presence and validity of a token passed in the request header. It also configures CORS
 * settings and logs access attempts, including the originating IP address and token.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

	private final TokenConfig propertyConfig;

	private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	public SecurityConfig(TokenConfig propertyConfig) {
		this.propertyConfig = propertyConfig;
	}

	/**
	 * Configures security settings for HTTP requests.
	 * <p>
	 * This method configures HTTP security, including the authorization rules, CORS settings,
	 * and form/login behavior. Access to "/api/**" endpoints is restricted based on a token in
	 * the request header. The IP address of the client is logged with the token for security
	 * monitoring purposes.
	 * </p>
	 *
	 * @param http the {@link HttpSecurity} object for configuring HTTP security.
	 * @return the {@link SecurityFilterChain} for the security configuration.
	 * @throws Exception if there is an error during configuration.
	 */
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers("/api").permitAll()
								.requestMatchers("/api/**")
								.access((authentication, context) -> {
									String token = context.getRequest().getHeader("token");
									boolean granted = propertyConfig.getAccessCode().equals(token);
									String remoteAddr = context.getRequest().getRemoteAddr();

									if (!granted)
										logger.warn("Access Denied for token: {} from IP: {}", token, remoteAddr);


									return new AuthorizationDecision(granted);
								})
								// Allow all other endpoints
								.anyRequest().permitAll()
				).formLogin(AbstractHttpConfigurer::disable);
		return http.build();
	}

	/**
	 * Configures CORS settings for the application.
	 * <p>
	 * This method configures the allowed origins, methods, and headers for cross-origin requests
	 * to the "/api/**" endpoints. It also ensures that credentials can be sent with requests.
	 * </p>
	 *
	 * @return the {@link CorsConfigurationSource} object containing the CORS configuration.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedHeader("Content-Type");
		corsConfig.addAllowedHeader("Authorization");
		corsConfig.addAllowedHeader("token");
		corsConfig.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", corsConfig);

		return source;
	}

}
