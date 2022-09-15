package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsAndrey() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Andrey"));
        Role result = store.findById("2");
        assertThat(result.getRoleName()).isEqualTo("Andrey");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Andrey"));
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAndrey() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Andrey"));
        store.add(new Role("2", "David"));
        Role result = store.findById("2");
        assertThat(result.getRoleName()).isEqualTo("Andrey");
    }

    @Test
    void whenReplaceThenRoleNameIsDavid() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        store.replace("3", new Role("3", "David"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("David");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        store.replace("20", new Role("20", "David"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Andrey");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        store.delete("3");
        Role result = store.findById("3");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAndrey() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        store.delete("20");
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Andrey");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        boolean rsl = store.replace("3", new Role("3", "David"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        boolean rsl = store.replace("20", new Role("30", "David"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        boolean rsl = store.delete("3");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Andrey"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}