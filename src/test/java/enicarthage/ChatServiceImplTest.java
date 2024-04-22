package com.example.demo;

import Exceptions.ChatNotFoundException;
import Exceptions.NoChatExistsInTheRepository;
import Entities.Chat;
import Repositories.ChatRepository;
import Services.ChatServiceImpl;
import Services.SequenceGeneratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChatServiceImplTest extends ProjetSpringApplicationTests {
	
	@Mock
    private ChatRepository chatRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private ChatServiceImpl chatService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddChat() {
        Chat chat = new Chat();
        when(sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME)).thenReturn((int) 1L);
        when(chatRepository.save(chat)).thenReturn(chat);

        Chat result = chatService.addChat(chat);

        assertNotNull(result);
        assertEquals(1L, result.getChatId());
        verify(sequenceGeneratorService, times(1)).generateSequence(Chat.SEQUENCE_NAME);
        verify(chatRepository, times(1)).save(chat);
    }

    @Test
    public void testFindAllChats() {
        List<Chat> chats = new ArrayList<>();
        when(chatRepository.findAll()).thenReturn(chats);

        assertThrows(NoChatExistsInTheRepository.class, () -> chatService.findallchats());

        verify(chatRepository, times(1)).findAll();
    }

    @Test
    public void testGetById() throws ChatNotFoundException {
        Chat chat = new Chat();
        chat.setChatId(1);
        Optional<Chat> optionalChat = Optional.of(chat);
        when(chatRepository.findById(1)).thenReturn(optionalChat);

        Chat result = chatService.getById(1);

        assertNotNull(result);
        assertEquals(chat, result);
        verify(chatRepository, times(1)).findById(1);
    }

}
