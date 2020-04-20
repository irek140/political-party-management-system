package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey7;

import java.util.List;

@Repository
public interface Survey7Repository extends JpaRepository<Survey7, Long> {
    List<Survey7> findByOwner(User user);
    List<Survey7> findByAddressees(User user);
}
