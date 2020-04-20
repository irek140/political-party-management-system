package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey5;

import java.util.List;

@Repository
public interface Survey5Repository extends JpaRepository<Survey5, Long> {
    List<Survey5> findByOwner(User user);
    List<Survey5> findByAddressees(User user);
}
