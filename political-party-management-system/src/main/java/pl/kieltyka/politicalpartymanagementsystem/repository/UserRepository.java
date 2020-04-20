package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByUsernameAndSendRequest(String username, boolean b);
    boolean existsByUsername(String username);
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    User findByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findByRegion(String region);
    List<User> findByConstituency(String constituency);
    List<User> findByAuthorities(String authority);
}
