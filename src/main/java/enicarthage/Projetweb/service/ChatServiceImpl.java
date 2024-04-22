package Services;

import Exceptions.ChatNotFoundException;
import Exceptions.NoChatExistsInTheRepository;
import Entities.Chat;
import Entities.Message;
import Repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
    private ChatRepository chatRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    
    public Chat addChat(Chat chat) {
        chat.setChatId(sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME));
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findallchats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }

    }

    @Override
    public Chat getById(int id) throws ChatNotFoundException {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        if (chatOptional.isPresent()) {
            return chatOptional.get();
        } else {
            throw new ChatNotFoundException("Chat with ID " + id + " not found");
        }
    }


    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);

        if (chat.isEmpty()) {
            throw new ChatNotFoundException("No chat found for username: " + username);
        } else {
            return chat;
        }
    }


    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException("No chat found for username: " + username);
        } else {
            return chat;
        }
    }
    
    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chatByFirstUser = chatRepository.getChatByFirstUserName(username);
        HashSet<Chat> chatBySecondUser = chatRepository.getChatBySecondUserName(username);

        HashSet<Chat> combinedChats = new HashSet<>();
        combinedChats.addAll(chatByFirstUser);
        combinedChats.addAll(chatBySecondUser);

        if (combinedChats.isEmpty()) {
            throw new ChatNotFoundException("No chat found for username: " + username);
        } else {
            return combinedChats;
        }
    }



    @Override
    public HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserNameAndFirstUserName(firstUserName, secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException("Chat between " + firstUserName + " and " + secondUserName + " not found");
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }

    @Override
    public Chat addMessage(Message add, int chatId) throws ChatNotFoundException {
        Optional<Chat> chat=chatRepository.findById(chatId);
        Chat abc=chat.get();

        if(abc.getMessageList()==null){
            List<Message> msg=new ArrayList<>();
            msg.add(add);
            abc.setMessageList(msg);
            return chatRepository.save(abc);
        }else{
            List<Message> rates=abc.getMessageList();
            rates.add(add);
            abc.setMessageList(rates);
            return chatRepository.save(abc);
        }
    }


}
