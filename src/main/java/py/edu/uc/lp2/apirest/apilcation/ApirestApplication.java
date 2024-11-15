package py.edu.uc.lp2.apirest.apilcation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"py.edu.uc.lp3","py.edu.uc.lp2.apirest"})
@ComponentScan(basePackages = {"py.edu.uc.lp3","py.edu.uc.lp2.apirest"})
@EntityScan(basePackages = {"py.edu.uc.lp3","py.edu.uc.lp2.apirest"})
@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}
}