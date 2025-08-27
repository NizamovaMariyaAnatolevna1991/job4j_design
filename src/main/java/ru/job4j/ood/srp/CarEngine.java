package ru.job4j.ood.srp;

import java.io.FileWriter;
import java.io.IOException;

public class CarEngine {
    /*
     * У класса три ответственности:
     * управление двигателем
     * логирование
     * работа с файлами
     * Если изменится формат логов или способ сохранения придется менять полностью класс
     */

    private double fuelConsumption;

    public void start() {
        System.out.println("Двигатель запущен.");
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void logToConsole(String message) {
        System.out.println("[LOG] " + message);
    }

    public void saveToFile(String filename, String data) {
        try (var writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (IOException e) {
            logToConsole("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
