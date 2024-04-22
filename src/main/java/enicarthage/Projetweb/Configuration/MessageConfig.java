package Configuration;

import org.springframework.context.annotation.Configuration;
import Entities.Message;
import org.springframework.context.annotation.Bean;

@Configuration
public class MessageConfig {
	
	@Bean
    Message message() {
        return new Message("Omar Koudhai", null, "Erreur 404");
    }
	
}
