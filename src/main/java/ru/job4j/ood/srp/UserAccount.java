package ru.job4j.ood.srp;

public class UserAccount {

    /*
    * У класса три ответсвенности:
    * валидация
    * хранение
    * отправка уведомлений
    *
    * Если изенятся правила валидации, придется менять класс
    * Если изменим способ хранения(файл/облако),придется менять класс
    * Перейдем с EMAIL на SMS ,придется менять класс
    *
     */
    private String email;
    private String password;

    // Ответственность 1: работа с пользователем
    public void register(String email, String password) {
        if (isValidEmail(email) && isValidPassword(password)) {
            this.email = email;
            this.password = password;
            saveToDatabase();
        } else {
            throw new IllegalArgumentException("Неверные данные");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    // Ответственность 2: сохранение в БД
    private void saveToDatabase() {
        System.out.println("Сохранение пользователя в базу: " + email);
        // Имитация сохранения
    }

    // Ответственность 3: отправка email
    public void sendWelcomeEmail() {
        System.out.println("Отправка приветственного письма на: " + email);
        // Логика отправки
    }
}
