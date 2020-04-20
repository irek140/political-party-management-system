package pl.kieltyka.politicalpartymanagementsystem.security;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private Logger log = Logger.getLogger(CustomAuthenticationManager.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPassEncoder;

    /*
     * Metoda do uwierzytelniania użytkownika
     * @param auth Obiekt typu Authentication (w naszym przypadku jest to UsernamePasswordAuthenticationToken) zawierający dane logowania
     * @return Authentication Gdy uwierzytelnianie użytkownika zakończy się pomyślnie, zwraca obiekt Authentication wzbogacony o uprawnienia
     * @exception AuthenticationException Wyrzuca wyjątek w przypadku, gdy nie istnieje użytkownik o podanej nazwie lub hasło
     * użytkownika nie jest prawidłowe.
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getPrincipal().toString();
        String password = auth.getCredentials().toString();

        log.info("Authentication user: " + username + " ...");

        User user = userRepo.findByUsername(username);

        if (user == null) throw new BadCredentialsException("Użytkownik " + username + " nie istnieje.");
        if (!bCryptPassEncoder.matches(password, user.getPassword())) throw new BadCredentialsException("Nieprawidłowe hasło dla użytkownika: " + username + ".");
        if (!user.isEnabled()) throw new BadCredentialsException("Użytkownik " + username + " nie został aktywowany. Sprawdź pocztę e-mail aby uzyskać link aktywacyjny.");
        if (user.isBanned()) throw new BadCredentialsException("Użytkownik " + username + " jest aktualnie zablokowany.");

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

}
