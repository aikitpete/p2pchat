/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import java.awt.image.BufferedImage;
import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.mock.ViewManagerMock;
import p2pchat.model.ErrorType;
import p2pchat.model.StatusType;
import p2pchat.model.User;

/**
 *
 * @author USER
 */
public class PeerManagerTest {

    ViewManagerMock viewMock;
    PeerManager instance;

    public PeerManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        viewMock = new ViewManagerMock();
        instance = new PeerManager(viewMock);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setStatus method, of class PeerManager.
     */
    @Test
    public void testSetStatusOnline() {
        System.out.println("Set Status Online");
        //Arrange
        StatusType newStatus = StatusType.ONLINE;

        //Act
        instance.setStatus(newStatus);

        //Assert
        assertEquals(instance.getStatus(), StatusType.ONLINE);//check status
        assertEquals(instance.getOnlineUsersNumber(), 0);//check online users number
    }

    /**
     * Test of setStatus method, of class PeerManager.
     */
    @Test
    public void testSetStatusOffline() {
        System.out.println("Set Status Offline");
        //Arrange
        StatusType newStatus = StatusType.OFFLINE;

        //Act
        instance.setStatus(newStatus);

        //Assert
        assertEquals(instance.getStatus(), StatusType.OFFLINE);//check status
        assertEquals(instance.getOnlineUsersNumber(), 0);//check online users number
    }

    /**
     * Test of getStatus method, of class PeerManager. Similar to tests above,
     * therefore this only tests the method without initialization
     */
    @Test
    public void testGetStatus() {
        System.out.println("Get Status");
        //Arrange
        StatusType status;

        //Act
        status = instance.getStatus();

        //Assert
        assertEquals(status, StatusType.OFFLINE);//check status
    }

    /**
     * Test of addOnlineUser method, of class PeerManager.
     */
    @Test
    public void testAddOnlineUser() {
        System.out.println("Add Online User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String name = "bob";
        String address = "localhost";
        int port = 2010;
        boolean result;

        //Act
        result = instance.addOnlineUser(name, address, port);

        //Assert
        assertEquals(result, true);
        assertEquals(instance.getOnlineUsersNumber(), 1);
    }

    /**
     * Test of addOnlineUser method, of class PeerManager.
     */
    @Test
    public void testAddOnlineUserOffline() {
        System.out.println("Add Online User Offline");
        //Arrange
        instance.setStatus(StatusType.OFFLINE);
        String name = "bob";
        String address = "localhost";
        int port = 2010;
        boolean result;

        //Act
        result = instance.addOnlineUser(name, address, port);

        //Assert
        assertEquals(result, false);
        assertEquals(instance.getOnlineUsersNumber(), 0);
        assertEquals(viewMock.displayError, ErrorType.NOT_ONLINE);
    }

    /**
     * Test of addOnlineUser method, of class PeerManager.
     */
    @Test
    public void testAddOnlineUserAlreadyChatting() {
        System.out.println("Add Online User Already Chatting");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String name = "bob";
        String address = "localhost";
        int port = 2010;
        boolean result;
        instance.addOnlineUser(name, address, port);

        //Act
        result = instance.addOnlineUser(name, address, port);

        //Assert
        assertEquals(result, false);
        assertEquals(instance.getOnlineUsersNumber(), 1);
        assertEquals(viewMock.displayError, ErrorType.FRIEND_ALREADY_CHATTING);
    }

    /**
     * Test of addOnlineUser method, of class PeerManager.
     */
    @Test
    public void testAddOnlineUserFull() {
        System.out.println("Add Online User Full");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String name = "bob";
        String address = "localhost";
        int port = 2010;
        boolean result;
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            instance.addOnlineUser("user" + i, address, port);
        }

        //Act
        result = instance.addOnlineUser(name, address, port);

        //Assert
        assertEquals(result, false);
        assertEquals(instance.getOnlineUsersNumber(), PeerManager.MAX_USERS);
        assertEquals(viewMock.displayError, ErrorType.FULL_ONLINE_USER_LIST);
    }

    /**
     * Test of getOnlineUserNames method, of class PeerManager.
     */
    @Test
    public void testGetOnlineUserNames() {
        instance.setStatus(StatusType.ONLINE);
        System.out.println("Get Online User Names");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        String users[] = new String[PeerManager.MAX_USERS];
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            users[i] = "user" + i;
            instance.addOnlineUser(users[i], address, port);
        }
        String names[];

        //Act
        names = instance.getOnlineUserNames();

        //Assert
        assertEquals(names.length, PeerManager.MAX_USERS);// check online user names
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            assertEquals(names[i], users[i]);
        }
    }

    /**
     * Test of getOnlineUserNames method, of class PeerManager.
     */
    @Test
    public void testGetOnlineUserNamesEmpty() {
        instance.setStatus(StatusType.ONLINE);
        System.out.println("Get Online User Names Empty");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        String names[];

        //Act
        names = instance.getOnlineUserNames();

        //Assert
        assertEquals(names.length, PeerManager.MAX_USERS);//check online user names
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            assertNull(names[i]);
        }
    }

    /**
     * Test of getFriendsPhotos method, of class PeerManager.
     */
    @Test
    public void testGetFriendsPhotos() {
        instance.setStatus(StatusType.ONLINE);
        System.out.println("Get Online User Photos");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        String users[] = new String[PeerManager.MAX_USERS];
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            users[i] = "user" + i;
            instance.addOnlineUser(users[i], address, port);
        }
        BufferedImage photos[];

        //Act
        photos = instance.getOnlineUserPhotos();

        //Assert
        assertEquals(photos.length, PeerManager.MAX_USERS);// check online user names
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            //assertNotNull(photos[i]);//Photos are not supported yet
        }
    }

    /**
     * Test of getOnlineUserNames method, of class PeerManager.
     */
    @Test
    public void testGetOnlineUserPhotosEmpty() {
        instance.setStatus(StatusType.ONLINE);
        System.out.println("Get Online User Photos Empty");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        BufferedImage photos[];

        //Act
        photos = instance.getOnlineUserPhotos();

        //Assert
        assertEquals(photos.length, PeerManager.MAX_USERS);//check online user names
        for (int i = 0; i < PeerManager.MAX_USERS; i++) {
            assertNull(photos[i]);
        }
    }

    /**
     * Test of isOnline method, of class PeerManager.
     */
    @Test
    public void testIsOnlineOnline() {
        System.out.println("Is Online Online");
        //Arrange
        StatusType status = StatusType.ONLINE;
        boolean result;
        instance.setStatus(status);

        //Act
        result = instance.isOnline();

        //Assert
        assertTrue(result);
    }

    /**
     * Test of isOnline method, of class PeerManager.
     */
    @Test
    public void testIsOnlineOffline() {
        System.out.println("Is Online Offline");
        //Arrange
        StatusType status = StatusType.OFFLINE;
        boolean result;
        instance.setStatus(status);

        //Act
        result = instance.isOnline();

        //Assert
        assertFalse(result);
    }

    /**
     * Test of getUsername method, of class PeerManager.
     */
    @Test
    public void testGetUsername() {
        System.out.println("Get Username");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String username = "bob";
        instance.setUser(username, "");
        String result;

        //Act
        result = instance.getCurrentUser();

        //Assert
        assertEquals(result, username);
    }

    /**
     * Test of getOnlineUsersNumber method, of class PeerManager.
     */
    @Test
    public void testGetOnlineUsersNumber() {
        System.out.println("Get Online Users Number");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser("user1", address, port);//add users
        instance.addOnlineUser("user2", address, port);
        instance.addOnlineUser("user3", address, port);
        int onlineUsersNumber;

        //Act
        onlineUsersNumber = instance.getOnlineUsersNumber();

        //Assert
        assertEquals(onlineUsersNumber, 3);
    }

    /**
     * Test of getOnlineUsersNumber method, of class PeerManager.
     */
    @Test
    public void testGetOnlineUsersNumberRandom() {
        System.out.println("Get Online Users Number Random");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser("user1", address, port);//randomly add/remove users
        instance.addOnlineUser("user2", address, port);
        instance.removeOnlineUser("user1");
        instance.addOnlineUser("user3", address, port);
        instance.addOnlineUser("user4", address, port);
        instance.removeOnlineUser("user2");
        instance.addOnlineUser("user2", address, port);
        int onlineUsersNumber;

        //Act
        onlineUsersNumber = instance.getOnlineUsersNumber();

        //Assert
        assertEquals(onlineUsersNumber, 3);
    }

    /**
     * Test of getUsers method, of class PeerManager.
     */
    @Test
    public void testGetUsers() {
        System.out.println("Get Users");
        //Arrange
        User result[];

        //Act
        result = instance.getUsers();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test of setUser method, of class PeerManager.
     */
    @Test
    public void testSetUser() {
        System.out.println("Set User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String username = "bob";
        String photo = "";

        //Act
        instance.setUser(username, photo);

        //Assert
        assertEquals(instance.getCurrentUser(), username);
    }

    /**
     * Test of removeOnlineUser method, of class PeerManager.
     */
    @Test
    public void testRemoveOnlineUser() {
        System.out.println("Remove Online User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        instance.addOnlineUser("user1", "localhost", 2010);
        instance.addOnlineUser("user2", "localhost", 2010);
        instance.addOnlineUser("user3", "localhost", 2010);
        boolean result;

        //Act
        result = instance.removeOnlineUser("user2");

        //Assert
        assertEquals(instance.getOnlineUsersNumber(), 2);
        assertTrue(result);
    }

    /**
     * Test of removeOnlineUser method, of class PeerManager.
     */
    @Test
    public void testRemoveOnlineUserNonexisting() {
        System.out.println("Remove Online User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        instance.addOnlineUser("user1", "localhost", 2010);
        instance.addOnlineUser("user2", "localhost", 2010);
        instance.addOnlineUser("user3", "localhost", 2010);
        boolean result;

        //Act
        result = instance.removeOnlineUser("foo");

        //Assert
        assertEquals(instance.getOnlineUsersNumber(), 3);
        assertFalse(result);
    }

    /**
     * Test of getCurrentUser method, of class PeerManager.
     */
    @Test
    public void testGetCurrentUser() {
        System.out.println("Get Current User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String username = "bob";
        String photo = "";
        instance.setUser(username, photo);
        String result;

        //Act
        result = instance.getCurrentUser();

        //Assert
        assertEquals(result, username);
    }

    /**
     * Test of getCurrentUser method, of class PeerManager.
     */
    @Test
    public void testGetCurrentUserOffline() {
        System.out.println("Get Current User Offline");
        //Arrange
        instance.setStatus(StatusType.OFFLINE);
        String username = "bob";
        String photo = "";
        instance.setUser(username, photo);
        String result;

        //Act
        result = instance.getCurrentUser();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.NOT_ONLINE);
        assertNull(result);
    }

    /**
     * Test of getCurrentUser method, of class PeerManager.
     */
    @Test
    public void testGetCurrentUserNull() {
        System.out.println("Get Current User Null");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String result;

        //Act
        result = instance.getCurrentUser();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.NOT_ONLINE);
        assertNull(result);
    }

    /**
     * Test of findOnlineUser method, of class PeerManager.
     */
    @Test
    public void testFindOnlineUser() {
        System.out.println("Find Online User");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String username = "bob";
        String photo = "";
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser("user1", address, port);//randomly add/remove users
        instance.addOnlineUser("user2", address, port);
        instance.removeOnlineUser("user1");
        instance.addOnlineUser("user3", address, port);
        instance.addOnlineUser("user4", address, port);
        instance.removeOnlineUser("user2");
        instance.addOnlineUser(username, address, port);
        User result;

        //Act
        result = instance.findOnlineUser(username);

        //Assert
        assertEquals(result.getUsername(), username);
    }

    /**
     * Test of findOnlineUser method, of class PeerManager.
     */
    @Test
    public void testFindOnlineUserNonexisting() {
        System.out.println("Find Online User Nonexisting");
        //Arrange
        instance.setStatus(StatusType.ONLINE);
        String username = "foo";
        String photo = "";
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser("user1", address, port);//randomly add/remove users
        instance.addOnlineUser("user2", address, port);
        instance.removeOnlineUser("user1");
        instance.addOnlineUser("user3", address, port);
        instance.addOnlineUser("user4", address, port);
        instance.removeOnlineUser("user2");
        instance.addOnlineUser("user5", address, port);
        User result;

        //Act
        result = instance.findOnlineUser(username);

        //Assert
        assertNull(result);
    }
}
