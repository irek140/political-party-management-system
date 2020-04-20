package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByName(String name);
}
