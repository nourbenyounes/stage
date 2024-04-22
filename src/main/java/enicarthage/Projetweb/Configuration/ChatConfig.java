package Configuration;

import org.springframework.context.annotation.Configuration;
import Entities.Chat;
import org.springframework.context.annotation.Bean;

@Configuration
public class ChatConfig {
	
	@Bean
    Chat chat() {
        return new Chat(0000, "omarkoudhai", "nourbenyouness", null);
    }

}
