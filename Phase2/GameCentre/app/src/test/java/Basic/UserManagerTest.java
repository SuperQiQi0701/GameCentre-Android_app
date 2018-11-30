package Basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {

    /**
     * A instance of UserManager for testing
     */
    private UserManager userManager;


    @Before
    public void setUp() throws Exception {
        this.userManager = new UserManager();
        this.userManager.signUp("@test user 1", "111111");
        this.userManager.signUp("@test user 2", "222222");
    }

    @After
    public void tearDown() throws Exception {
        this.userManager = null;
    }

    @Test
    public void login() {
        assertEquals("@test user 1", this.userManager.login("@test user 1", "111111"));
        assertEquals("", this.userManager.login("@test user 6", "666666"));
    }

    @Test
    public void exist() {
        assertTrue(this.userManager.exist("@test user 1"));
        assertTrue(! this.userManager.exist("@test user 6"));
    }

    @Test
    public void signUp() {
        this.userManager.signUp("@test user 2", "222222");
        assertTrue(this.userManager.exist("@test user 2"));
        assertEquals("@test user 2", this.userManager.login("@test user 2", "222222"));
    }

    @Test
    public void getUser() {
        assertEquals("@test user 1", this.userManager.getUser("@test user 1").getUserName());
        assertNull(this.userManager.getUser("@test user 3"));

    }

    @Test
    public void checkUserNameValidity() {
        assertEquals("OK!", this.userManager.checkUserNameValidity("@"));
        assertEquals("Please enter a valid e-mail", this.userManager.checkUserNameValidity(""));
        assertEquals("E-mail exist", this.userManager.checkUserNameValidity("@test user 1"));


    }

    @Test
    public void isValidPassword() {
        assertTrue(this.userManager.isValidPassword("111111"));
        assertTrue(! this.userManager.isValidPassword("1"));

    }
}