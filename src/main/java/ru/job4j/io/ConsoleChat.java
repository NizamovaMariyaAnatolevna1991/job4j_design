package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        if (phrases.isEmpty()) {
            System.out.println("Файл с ответами бота пуст или не найден.");
        }

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isBotActive = true;
        boolean running = true;

        System.out.println("Чат начался. Введите сообщение или команду.");
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            log.add("User: " + input);

            if (OUT.equalsIgnoreCase(input)) {
                log.add("Bot: Завершение работы.");
                running = false;
            } else if (STOP.equalsIgnoreCase(input)) {
                isBotActive = false;
                log.add("Bot: Я замолкаю.");
            } else if (CONTINUE.equalsIgnoreCase(input)) {
                isBotActive = true;
                log.add("Bot: Я снова с вами.");
            } else {
                if (isBotActive) {
                    String botResponse = phrases.get(random.nextInt(phrases.size()));
                    System.out.println("Bot: " + botResponse);
                    log.add("Bot: " + botResponse);
                }
            }
        }

        saveLog(log);
        scanner.close();
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try {
            phrases = Files.readAllLines(Path.of(botAnswers));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла с ответами бота: " + e.getMessage());
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logFile = "data/chat_log.txt";
        String botAnswersFile = "data/bot_answers.txt";
        ConsoleChat consoleChat = new ConsoleChat(logFile, botAnswersFile);
        consoleChat.run();
    }
}
