package lk.ijse.gdse68.aad.spring_pos_system_api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("lk.ijse.gdse68.aad.spring_pos_system_api")
@EnableWebMvc
@EnableJpaRepositories("lk.ijse.gdse68.aad.spring_pos_system_api")
@EnableTransactionManagement
public class WebAppConfig {
}
