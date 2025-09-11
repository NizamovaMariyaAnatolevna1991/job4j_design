package ru.job4j.ood.isp;

/**
 * UserService жестко привязан к EmailSender
 * если захотим отправлять по SMS или Telegram
 * придется менять код UserService
 */

public class UserService {
    private EmailSender sender = new EmailSender();

    public void register(String email) {
        sender.send(email, "Добро пожаловать!");
    }
}

class EmailSender {
    public void send(String to, String msg) {
        System.out.println("Отправлено на email: " + msg);
    }
}
