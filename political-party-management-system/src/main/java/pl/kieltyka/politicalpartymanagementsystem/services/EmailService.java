package pl.kieltyka.politicalpartymanagementsystem.services;

import pl.kieltyka.politicalpartymanagementsystem.model.Task;
import pl.kieltyka.politicalpartymanagementsystem.model.User;

public interface EmailService {
    void sendActivationEmailToUser(User userToActivate);
    void sendMessageNotification(User user);
    void sendTaskNotification(User user);
    void sendAcceptTaskNotification(Task task);
    void sendFinishTaskNotification(Task task);
    void sendCorrectTaskNotification(Task task);
    void sendSurveyNotification(User user);

}
