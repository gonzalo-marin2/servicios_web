package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
				aut->aut.requestMatchers(HttpMethod.GET, "/libros").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.anyRequest().permitAll()//acceso libre al resto de llamadas
				)
		//3.- establecer como se va a autenticar el usuario
		.oauth2ResourceServer(oauth2ResourceServer-> 
	 		oauth2ResourceServer.jwt(jwt->jwt 
	 				.jwtAuthenticationConverter(jwtAuthConverter))) 
	 				.sessionManagement(sessionManagement-> 
	 					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//4.- retornar el objeto http ya construido
		return http.build();
	}
}
