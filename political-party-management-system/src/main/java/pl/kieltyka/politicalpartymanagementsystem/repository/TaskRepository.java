package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.Task;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAddressees(User user);

    List<Task> findByPrincipal(User user);

    List<Task> findByRecipient(User user);
}
