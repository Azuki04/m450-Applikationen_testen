package ch.web.web_shop.model;

import static org.junit.Assert.assertEquals;

import ch.web.web_shop.model.User;
import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUser() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        boolean subscribed = true;
        String password = "password";

        User user = new User(id, name, email, subscribed, password);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(subscribed, user.isSubscribed());
        assertEquals(password, user.getPassword());
    }
}
