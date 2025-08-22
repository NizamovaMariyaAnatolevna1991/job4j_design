package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    void whenNoSessionsMatchFilterThenFindReturnsEmptyList() {
        Cinema cinema = new Cinema3D();
        List<Session> found = cinema.find(s -> s.price() > 1000);
        assertTrue(found.isEmpty());
    }

    @Test
    void whenAddMultipleSessionsThenFindFiltersCorrectly() {
        Cinema cinema = new Cinema3D();
        Session s1 = new MovieSession("Форсаж", 1, 10, 15, someDate);
        Session s2 = new MovieSession("Аватар", 2, 20, 12, someOtherDate);

        cinema.add(s1);
        cinema.add(s2);

        List<Session> fastAndFurious = cinema.find(s -> s.name().contains("Форсаж"));
        assertEquals(1, fastAndFurious.size());
        assertEquals("Форсаж", fastAndFurious.get(0).name());
    }


    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenBuyTicketForNonexistentSessionThenThrowOrReturnNull() {
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Account account = new UserAccount("john");

        Ticket ticket = cinema.buy(account, 1, 1, date);

        assertNull(ticket);
    }

    @Test
    void whenBuyWithInvalidRowOrColumnThenReturnNull() {
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.add(new MovieSession("Матрица", 1, 10, 10, date));

        Ticket ticket = cinema.buy(new UserAccount("anna"), 99, 99, date);

        assertNull(ticket);
    }

    @Test
    void whenBuyWithNullAccountThenThrowException() {
        Cinema cinema = new Cinema3D();
        assertThrows(NullPointerException.class,
                () -> cinema.buy(null, 1, 1, Calendar.getInstance()));
    }

    @Test
    void whenBuyWithNullDateThenReturnNull() {
        Cinema cinema = new Cinema3D();
        Ticket ticket = cinema.buy(new UserAccount("tom"), 1, 1, null);
        assertNull(ticket);
    }

    @Test
    void whenFindWithNullPredicateThenThrowException() {
        Cinema cinema = new Cinema3D();
        assertThrows(NullPointerException.class,
                () -> cinema.find(null));
    }
}
