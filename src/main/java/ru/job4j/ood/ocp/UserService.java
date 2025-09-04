package ru.job4j.ood.ocp;

/**
 * Класс, который сам решает куда логировать процесс регистрации пользователя
 * а что если мы решим писать логи не БД а в файл
 * или совсем захотим  отключить логирование, придется менять класс
 */

public class UserService {

    public void registerUser(String email) {
        System.out.println("Начало регистрации: " + email);

        if (isValidEmail(email)) {
            System.out.println("Email валиден: " + email);
            saveToDatabase(email);
            System.out.println("Пользователь сохранён: " + email);
        } else {
            System.err.println("Ошибка: неверный email — " + email);
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private void saveToDatabase(String email) {
        System.out.println("Сохранение в БД: " + email);
    }
}
