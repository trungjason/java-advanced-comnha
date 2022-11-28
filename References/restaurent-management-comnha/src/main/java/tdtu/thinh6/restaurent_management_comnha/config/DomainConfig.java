package tdtu.thinh6.restaurent_management_comnha.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("tdtu.thinh6.restaurent_management_comnha.domain")
@EnableJpaRepositories("tdtu.thinh6.restaurent_management_comnha.repos")
@EnableTransactionManagement
public class DomainConfig {
}
