package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthConverter jwtAuthConverter;
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		//1.- desactivar el filtro
		http.csrf(c->c.disable())
		//2.- establecer criterios de acceso
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/curso").authenticated()
				.requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE, "/eliminar").hasAnyRole("ADMINS","OPERATORS")
				.requestMatchers(HttpMethod.PUT, "/actualizar").hasAnyRole("OPERATORS")
				.anyRequest().permitAll()//acceso libre al resto de llamadas
				)
		//3.- establecer cómo se va a autenticar el usuario, los datos los proporciona el conversor
		.oauth2ResourceServer(oauth2ResourceServer-> 
		 	oauth2ResourceServer.jwt(jwt->jwt 
		 			.jwtAuthenticationConverter(jwtAuthConverter))) 
		 			.sessionManagement(sessionManagement-> 
		 				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//4.- retornar el objeto http ya construido
		return http.build();
	}
	
}
