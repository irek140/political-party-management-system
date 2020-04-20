package pl.kieltyka.politicalpartymanagementsystem.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.model.surveys.Survey4;

import java.util.List;

@Repository
public interface Survey4Repository extends JpaRepository<Survey4, Long>{
    List<Survey4> findByOwner(User user);
    List<Survey4> findByAddressees(User user);
}
