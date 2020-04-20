package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey6;

import java.util.List;

@Repository
public interface Survey6Repository extends JpaRepository<Survey6, Long> {
    List<Survey6> findByOwner(User user);
    List<Survey6> findByAddressees(User user);
}
