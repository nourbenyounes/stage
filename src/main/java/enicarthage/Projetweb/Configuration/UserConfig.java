package Configuration;

import org.springframework.context.annotation.Configuration;
import Entities.User;
import org.springframework.context.annotation.Bean;

@Configuration
public class UserConfig {

    @Bean
    User user() {
        return new User("Omar Koudhai");
    }

}
