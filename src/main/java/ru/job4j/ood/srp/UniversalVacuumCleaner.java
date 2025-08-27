package ru.job4j.ood.srp;

public class UniversalVacuumCleaner implements VacuumCleaner {
    /*
    * Класс отвечает за 2 разные вещи
     */

    @Override
    public void dryClean() {
        System.out.println("Выполняется сухая уборка: всасывание пыли...");
    }

    @Override
    public void wetClean() {
        System.out.println("Выполняется влажная уборка: подача воды...");
    }
}
