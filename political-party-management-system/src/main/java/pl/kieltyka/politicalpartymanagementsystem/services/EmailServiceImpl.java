package pl.kieltyka.politicalpartymanagementsystem.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.kieltyka.politicalpartymanagementsystem.model.ActivationCode;
import pl.kieltyka.politicalpartymanagementsystem.model.Task;
import pl.kieltyka.politicalpartymanagementsystem.model.User;
import pl.kieltyka.politicalpartymanagementsystem.repository.ActivationCodeRepository;
import pl.kieltyka.politicalpartymanagementsystem.repository.UserRepository;

import java.util.UUID;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ActivationCodeRepository codeRepo;

    @Autowired
    private UserRepository userRepo;

    private static String URL_ROOT = "http://localhost:4200";
    private static String SUBJECT = "System Zarządzania Partią - Link aktywacyjny";

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendActivationEmailToUser(User userToActivate) {
        String userEmail = userToActivate.getEmail();
        String code = UUID.randomUUID().toString();
        ActivationCode activationCode = new ActivationCode(code);

        activationCode.setUser(userToActivate);
        activationCode = codeRepo.save(activationCode);

        userToActivate.setActivationCode(activationCode);
        userRepo.save(userToActivate);

        String mailContent = "Kliknij w poniższy link, aby aktywować konto: \n";
        mailContent += URL_ROOT + "/" + activationCode.getId() + "/" + code;
        sendSimpleMessage(userEmail, SUBJECT, mailContent);
    }

    @Override
    public void sendMessageNotification(User user) {
        String userEmail = user.getEmail();
        String name = user.getName();

        String mailContent = "Witaj " + name + ", w Systemie zarządzania partią czeka na Ciebie nowa wiadomość.";
        sendSimpleMessage(userEmail, "Nowa wiadomość", mailContent);
    }

    @Override
    public void sendTaskNotification(User user) {
        String userEmail = user.getEmail();
        String name = user.getName();

        String mailContent = "Witaj " + name + ", w Systemie zarządzania partią czeka na Ciebie nowe zadanie.";
        sendSimpleMessage(userEmail, "Nowe zadanie", mailContent);
    }

    @Override
    public void sendAcceptTaskNotification(Task task) {
        String userEmail = task.getPrincipal().getEmail();
        String name = task.getPrincipal().getName();
        String recipientName = task.getRecipient().getName();
        String recipientSurname = task.getRecipient().getSurname();

        String mailContent = "Witaj " + name + ", użytkownik " + recipientName + " " + recipientSurname + " rozpoczął realizację twojego zadania: " + task.getTaskTitle();
        sendSimpleMessage(userEmail, "Zadanie w toku", mailContent);
    }

    @Override
    public void sendFinishTaskNotification(Task task) {
        String userEmail = task.getPrincipal().getEmail();
        String name = task.getPrincipal().getName();
        String recipientName = task.getRecipient().getName();
        String recipientSurname = task.getRecipient().getSurname();

        String mailContent = "Witaj " + name + ", użytkownik " + recipientName + " " + recipientSurname + " informuje o zakończeniu zadania: " + task.getTaskTitle();
        sendSimpleMessage(userEmail, "Zadanie zakończone", mailContent);
    }

    @Override
    public void sendCorrectTaskNotification(Task task) {
        String userEmail = task.getRecipient().getEmail();
        String name = task.getRecipient().getName();
        String principalName = task.getPrincipal().getName();
        String principalSurname = task.getPrincipal().getSurname();

        String mailContent = "Witaj " + name + ", " + principalName + " " + principalSurname + " informuje o konieczności poprawienia zadania: " + task.getTaskTitle();
        sendSimpleMessage(userEmail, "Zadanie wymaga poprawy", mailContent);
    }

    @Override
    public void sendSurveyNotification(User user) {
        String userEmail = user.getEmail();
        String name = user.getName();

        String mailContent = "Witaj " + name + ", w Systemie zarządzania partią czeka na Ciebie nowe głosowanie lub sondaż.";
        sendSimpleMessage(userEmail, "Nowe głosowanie/sondaż", mailContent);
    }
}