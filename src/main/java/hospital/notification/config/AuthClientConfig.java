package hospital.notification.config;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class AuthClientConfig {
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuerUri;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/public/**").permitAll().anyRequest().authenticated())
				.anonymous(anonymous -> anonymous.disable()).oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))))
				.build();
	}

	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		// Converter for roles
		JwtGrantedAuthoritiesConverter rolesConverter = new JwtGrantedAuthoritiesConverter();
		rolesConverter.setAuthoritiesClaimName("roles");
		rolesConverter.setAuthorityPrefix("ROLE_");

		// Converter for scopes
		JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();
		scopesConverter.setAuthoritiesClaimName("scope");
		scopesConverter.setAuthorityPrefix("SCOPE_");

		// Combined converter
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			Collection<GrantedAuthority> rolesAuthorities = rolesConverter.convert(jwt);
			Collection<GrantedAuthority> scopesAuthorities = scopesConverter.convert(jwt);
			return Stream.concat(rolesAuthorities.stream(), scopesAuthorities.stream()).collect(Collectors.toSet());
		});
		return jwtAuthenticationConverter;
	}

	@Bean
	RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl
				.fromHierarchy("SUPERADMIN > ADMIN\n" + "ADMIN > DOCTOR\n" + "DOCTOR > NURSE\n" + "NURSE > USER");
	}

	@Bean
	MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		return expressionHandler;
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
