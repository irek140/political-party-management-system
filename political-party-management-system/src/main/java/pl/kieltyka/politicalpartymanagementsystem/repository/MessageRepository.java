package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.Message;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByAddressees(User user);
    List<Message> findByRecipient(User user);
    List<Message> findByPrincipal(User user);
}