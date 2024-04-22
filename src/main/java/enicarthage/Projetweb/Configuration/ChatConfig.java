package enicarthage.Projetweb.Configuration;
import enicarthage.Projetweb.entity.Chat;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ChatConfig {
	
	@Bean
    Chat chat() {
        return new Chat(0000, "omarkoudhai", "nourbenyouness", null);
    }

}
