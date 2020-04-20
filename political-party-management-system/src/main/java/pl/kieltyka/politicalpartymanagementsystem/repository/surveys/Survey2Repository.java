package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey2;

import java.util.List;

@Repository
public interface Survey2Repository extends JpaRepository<Survey2, Long> {
    List<Survey2> findByOwner(User user);
    List<Survey2> findByAddressees(User user);
}
