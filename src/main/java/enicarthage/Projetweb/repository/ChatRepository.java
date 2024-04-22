package enicarthage.Projetweb.repository;


import enicarthage.Projetweb.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
	
	HashSet<Chat> getChatByFirstUserName(String username);

    HashSet<Chat> getChatBySecondUserName(String username);

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

    HashSet<Chat> getChatBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);
}

