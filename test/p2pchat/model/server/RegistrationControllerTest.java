/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.model.User;
import p2pchat.persistence.server.IPersistenceUser;
import p2pchat.persistence.server.PersistenceUserMock;

/**
 *
 * @author USER
 */
public class RegistrationControllerTest {

    RegistrationController instance;
    IPersistenceUser persistenceUser;
    //New user
    final String usernameNew = "colin";
    final String passwordNew = "colin";
    //Existing user
    final String usernameBob = "bob";
    final String passwordBob = "bob";

    public RegistrationControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new RegistrationController();
        persistenceUser = new PersistenceUserMock();
        PersistentUser.setPersistence(persistenceUser);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class RegistrationController.
     */
    @Test
    public void testRegisterNormal() {
        //Arrange
        System.out.println("Register Normal");

        //Act
        Boolean ret = instance.register(usernameNew, passwordNew, passwordNew, "");

        //Assert
        assertTrue(ret);
    }

    /**
     * Test of register method, of class RegistrationController with existing
     * username.
     */
    @Test
    public void testRegisterExistingUsername() {
        //Arrange
        System.out.println("Register Existing Username");

        //Act
        Boolean ret = instance.register(usernameBob, passwordNew, passwordNew, "");

        //Assert
        assertFalse(ret);
    }

    /**
     * Test of register method, of class RegistrationController with existing
     * password.
     */
    @Test
    public void testRegisterExistingPassword() {
        //Arrange
        System.out.println("Register Existing Password");

        //Act
        Boolean ret = instance.register(usernameNew, passwordBob, passwordBob, "");

        //Assert
        assertTrue(ret);
    }

    /**
     * Test of register method, of class RegistrationController with not
     * matching passwords.
     */
    @Test
    public void testRegisterNotMatching() {
        //Arrange
        System.out.println("Register Not Matching Password");

        //Act
        Boolean ret = instance.register(usernameNew, passwordNew, "foo", "");

        //Assert
        assertFalse(ret);
    }

    /**
     * Test of register method, of class RegistrationController with username too short.
     */
    @Test
    public void testRegisterUsernameShort() {
        //Arrange
        System.out.println("Register Username Too Short");

        //Act
        Boolean ret = instance.register("", passwordNew, passwordNew, "");

        //Assert
        assertFalse(ret);
    }
    
    /**
     * Test of register method, of class RegistrationController with username not set.
     */
    @Test
    public void testRegisterUsernameNull() {
        //Arrange
        System.out.println("Register Username Not Set");

        //Act
        Boolean ret = instance.register(null, passwordNew, passwordNew, "");

        //Assert
        assertFalse(ret);
    }
    
    /**
     * Test of register method, of class RegistrationController with password too short.
     */
    @Test
    public void testRegisterPasswordShort() {
        //Arrange
        System.out.println("Register Password Too Short");

        //Act
        Boolean ret = instance.register(usernameNew, "", "", "");

        //Assert
        assertFalse(ret);
    }
    
    /**
     * Test of register method, of class RegistrationController with password not set.
     */
    @Test
    public void testRegisterPasswordNull() {
        //Arrange
        System.out.println("Register Password Not Set");

        //Act
        Boolean ret = instance.register(usernameNew, null, null, "");

        //Assert
        assertFalse(ret);
    }
}
