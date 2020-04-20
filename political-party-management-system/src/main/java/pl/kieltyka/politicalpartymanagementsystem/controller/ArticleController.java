package pl.kieltyka.politicalpartymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kieltyka.politicalpartymanagementsystem.model.Article;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping(value="/articles")
    public List<Article> getAllArticles() {
        System.out.println("Get accepted Articles...");
        List<Article> allArticles = new ArrayList<>();
        articleRepository.findAll().forEach(allArticles::add);

        List<Article> acceptedArticles = new ArrayList<>();
        for(Article article : allArticles) {
            if(article.isAccepted())
                acceptedArticles.add(article);
        }
        Collections.reverse(acceptedArticles);
        return acceptedArticles;
    }

    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
    @GetMapping(value="/articles/unacceptable")
    public List<Article> getNotAcceptedArticles() {
        System.out.println("Get unacceptable Articles...");
        List<Article> allArticles = new ArrayList<>();
        articleRepository.findAll().forEach(allArticles::add);

        List<Article> unacceptableArticles = new ArrayList<>();
        for(Article article : allArticles) {
            if(article.isAccepted()){
            } else {
                unacceptableArticles.add(article);
            }
        }
        return unacceptableArticles;
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')" +
            " or hasAuthority('ROLE_MEMBER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/articles/create", consumes = {"application/json"}, produces = {"application/json"})
    public Article createArticle(@RequestBody Article article) {
       if(article instanceof Article) {
           System.out.println("Create new article...");
           UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           userDetails.getUsername();
           Article _article = articleRepository.save(new Article(article.getTitle(), article.getContent(), (User) userDetails));
           return _article;
       } else {
           return null;
       }
    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @DeleteMapping("/articles/delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") long id) {
        System.out.println("Delete Article with ID = " + id + "...");
        articleRepository.deleteById(id);
        return new ResponseEntity<>("Article has been deleted!", HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('ROLE_MEMBER')")
//    @PutMapping("/articles/update/{id}")
//    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article article) {
//        System.out.println("Update Article with ID = " + id + "...");
//        Optional<Article> articleData = articleRepository.findById(id);
//        if (articleData.isPresent()) {
//            Article _article = articleData.get();
//            _article.setTitle(article.getTitle());
//            _article.setContent(article.getContent());
//            _article.setAuthor(article.getAuthor());
//            return new ResponseEntity<>(articleRepository.save(_article), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PreAuthorize("hasAuthority('ROLE_PARTY_PRESIDENT') or hasAuthority('ROLE_PARTY_VICE_PRESIDENT') or hasAuthority('ROLE_PARTY_SECRETARY')" +
            " or hasAuthority('ROLE_REGION_PRESIDENT') or hasAuthority('ROLE_REGION_VICE_PRESIDENT') or hasAuthority('ROLE_REGION_SECRETARY')" +
            " or hasAuthority('ROLE_CONSTITUENCY_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_VICE_PRESIDENT') or hasAuthority('ROLE_CONSTITUENCY_SECRETARY')")
    @PutMapping(value = "/articles/accept/{id}")
    public ResponseEntity<Article> acceptArticle(@PathVariable("id") long id) {
        System.out.println("Accept Article with ID = " + id + "...");
        Optional<Article> articleData = articleRepository.findById(id);
        if (articleData.isPresent()) {
            Article _article = articleData.get();
            _article.setAccepted(true);

            return new ResponseEntity<>(articleRepository.save(_article), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
