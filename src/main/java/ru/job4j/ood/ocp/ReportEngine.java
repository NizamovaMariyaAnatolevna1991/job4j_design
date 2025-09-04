package ru.job4j.ood.ocp;

/**
 * Мы создали класс, который генерирует отчет в простом виде
 * Теперь нам нужно отчеты для разных отделов
 * Каждый раз, когда нам нужно что-то новое
 * мы лезем менять этот класс, риск сломать уже работающее
 */

public class ReportEngine {
    public String generate(String type) {
        if ("accounting".equals(type)) {
            return "Name; Salary(USD);\nIvan; 1.5;";
        } else if ("hr".equals(type)) {
            return "Name; Salary;\nIvan; 100;\nMaria; 300;";
        } else if ("it".equals(type)) {
            return "Name,Hired,Fired,Salary\nIvan,2025-01-01,2025-01-01,100.0";
        } else {
            return "Unknown report type";
        }
    }
}
