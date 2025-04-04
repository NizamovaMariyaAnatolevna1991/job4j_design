package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsDartaniyan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Dartaniyan");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsDartaniyan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        store.add(new Role("1", "Aramis"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Dartaniyan");
    }

    @Test
    void whenReplaceThenRolenameIsAramis() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        store.replace("1", new Role("1", "Aramis"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Aramis");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        store.replace("10", new Role("10", "Aramis"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Dartaniyan");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsDartaniyan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Dartaniyan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        boolean result = store.replace("1", new Role("1", "Aramis"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Dartaniyan"));
        boolean result = store.replace("10", new Role("10", "Aramis"));
        assertThat(result).isFalse();
    }
}