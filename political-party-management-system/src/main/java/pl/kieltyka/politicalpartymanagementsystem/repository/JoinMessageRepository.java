package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.JoinMessage;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface JoinMessageRepository extends JpaRepository<JoinMessage, Long> {

    List<JoinMessage> findAll();
    Optional<JoinMessage> findByAuthor(User user);


    JoinMessage findAllById(Long id);
   // JoinMessage findAllByUsername(String username);
}
