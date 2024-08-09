package stage_test.taskmmanagerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TaskmManagerAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskmManagerAppApplication.class, args);
    }
  @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }





}
