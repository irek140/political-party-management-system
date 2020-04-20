package pl.kieltyka.politicalpartymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kieltyka.politicalpartymanagementsystem.model.Article;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAll();
    List<Article> findByAuthor(User user);
    List<Article> findByAuthor(Long id);

}