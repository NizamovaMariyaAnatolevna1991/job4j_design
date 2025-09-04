package ru.job4j.ood.ocp;

/**
 * Мы создали класс, который генерирует отчет в простом виде
 * Теперь нам нужно отчеты для разных отделов
 * Каждый раз, когда нам нужно что-то новое
 * мы лезем менять этот класс, риск сломать уже работающее
 */

public class ReportEngine {
    String generate() {
        if ("accounting".equals(type)) {
            // конвертация в USD
        } else if ("hr".equals(type)) {
            // сортировка
        } else if ("it".equals(type)) {
            // CSV
        }
    }
}
