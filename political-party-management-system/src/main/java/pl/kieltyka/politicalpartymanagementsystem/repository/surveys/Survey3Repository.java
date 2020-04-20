package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey3;

import java.util.List;

@Repository
public interface Survey3Repository extends JpaRepository<Survey3, Long> {
    List<Survey3> findByOwner(User user);
    List<Survey3> findByAddressees(User user);
}
