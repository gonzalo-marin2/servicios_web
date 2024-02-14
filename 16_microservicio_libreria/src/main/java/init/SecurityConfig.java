package init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public JdbcUserDetailsManager users(){
		DriverManagerDataSource ds= new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity");
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbc=new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		return jdbc;
	}
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		//1.- desactivar el filtro
		http.csrf(c->c.disable())
		//2.- establecer criterios de acceso
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/isbn").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.anyRequest().permitAll()//acceso libre al resto de llamadas
				)
		//3.- establecer como se va a autenticar el usuario
		.httpBasic(Customizer.withDefaults());
		//4.- retornar el objeto http ya construido
		return http.build();
	}
}
