package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.Vote;

import java.util.List;
import java.util.Optional;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByUserid(long id);

}
