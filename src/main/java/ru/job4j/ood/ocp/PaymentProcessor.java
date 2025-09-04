package ru.job4j.ood.ocp;

/**
 * У нас есть класс,который умеет обрабатывать 2 способа оплаты
 * Но, что если появился новый способ оплаты, нужно менять класс
 */

public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if ("credit_card".equals(paymentType)) {
            System.out.println("Обработка оплаты по карте: " + amount);
        } else if ("paypal".equals(paymentType)) {
            System.out.println("Обработка PayPal-платежа: " + amount);
        } else {
            throw new IllegalArgumentException("Неизвестный тип платежа: " + paymentType);
        }
    }
}
