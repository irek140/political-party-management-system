package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.Constituency;

import java.util.List;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
    Constituency findByName(String name);
    List<Constituency> findByParent(String region);
}