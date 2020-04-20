package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.ActivationCode;

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {

}
