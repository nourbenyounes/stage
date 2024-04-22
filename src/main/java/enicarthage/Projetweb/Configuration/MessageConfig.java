package enicarthage.Projetweb.Configuration;

import org.springframework.context.annotation.Configuration;
import enicarthage.Projetweb.entity.*;
import org.springframework.context.annotation.Bean;

@Configuration
public class MessageConfig {
	
	@Bean
    Message message() {
        return new Message("Omar Koudhai", null, "Erreur 404");
    }
	
}
