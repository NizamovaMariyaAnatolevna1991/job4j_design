package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.CalendarAdapter;

import java.util.Calendar;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlElement
    private String name;

    @XmlElement
    @XmlJavaTypeAdapter(CalendarAdapter.class)
    private Calendar hired;

    @XmlElement
    @XmlJavaTypeAdapter(CalendarAdapter.class)
    private Calendar fired;

    @XmlElement
    private double salary;

    private Currency salaryCurrency;

    public Employee() {
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary, Currency salaryCurrency) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public Currency getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(Currency salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}