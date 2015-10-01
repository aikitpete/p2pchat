/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.mock.OnlineUserManagerPeerMock;
import p2pchat.mock.ConnectionManagerPeerMock;
import p2pchat.mock.ViewManagerMock;
import p2pchat.model.ErrorType;
import p2pchat.model.Message;
import p2pchat.model.MessageFactory;
import p2pchat.model.StatusType;

/**
 *
 * @author USER
 */
public class PeerMessageHandlerTest {

    OnlineUserManagerPeerMock peerManager;
    ConnectionManagerPeerMock connectionManager;
    ViewManagerMock viewManager;
    PeerMessageHandler instance;

    public PeerMessageHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        peerManager = new OnlineUserManagerPeerMock();
        connectionManager = new ConnectionManagerPeerMock();
        viewManager = new ViewManagerMock();
        instance = new PeerMessageHandler(connectionManager, peerManager, viewManager);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataAddFriendAcceptMessage() {
        System.out.println("Handle Data Server to Peer AddFriendAcceptMessage");
        //Arrange
        Message message = MessageFactory.createAddFriendAcceptMessage("bob", "localhost", 2010);

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(peerManager.addOnlineUserUsername, "bob");//new online user name
        assertEquals(peerManager.addOnlineUserAddress, "localhost");//new online user address
        assertEquals(peerManager.addOnlineUserPort, 2010);//new online user port
        assertTrue(viewManager.setFocus);//set focus to main window
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataAddFriendRejectMessage() {
        System.out.println("Handle Data Server to Peer AddFriendRejectMessage");
        //Arrange
        Message message = MessageFactory.createAddFriendRejectMessage("bob", ErrorType.REMOTE_ACTION_UNSUCCESSFUL);

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(peerManager.removeOnlineUser, "bob");//remove online user
        assertEquals(viewManager.displayError, ErrorType.REMOTE_ACTION_UNSUCCESSFUL);//display error
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataLoginAcceptMessage() {
        System.out.println("Handle Data Server to Peer LoginAcceptMessage");
        //Arrange
        Message message = MessageFactory.createLoginAcceptMessage("bob", "localhost", 2010);

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(peerManager.setUserUsername, "bob");//set current user
        assertNull(peerManager.setUserPhoto);//not yet implemented
        assertEquals(viewManager.setUserImage, "profile/bob.jpg");//update view profile picture
        assertEquals(connectionManager.startPeer, 2010);//set current port
        assertTrue(viewManager.setFocus);//set focus to main window
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataLoginRejectMessage() {
        System.out.println("Handle Data Server to Peer LoginRejectMessage");
        //Arrange
        Message message = MessageFactory.createLoginRejectMessage("bob", ErrorType.REMOTE_ACTION_UNSUCCESSFUL);

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(viewManager.displayError, ErrorType.REMOTE_ACTION_UNSUCCESSFUL);
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataLogoutAcceptMessage() {
        System.out.println("Handle Data Server to Peer LogoutAcceptMessage - not used");
        //not used
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataLogoutRejectMessage() {
        System.out.println("Handle Data Server to LogoutRejectMessage - not used");
        //not used
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataRegistrationAcceptMessage() {
        System.out.println("Handle Data Server to Peer RegistrationAcceptMessage");
        //Arrange
        Message message = MessageFactory.createRegistrationAcceptMessage("bob");

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertTrue(viewManager.setFocus);//set focus on main window
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataRegistrationRejectMessage() {
        System.out.println("Handle Data Server to RegistrationRejectMessage");
        //Arrange
        Message message = MessageFactory.createRegistrationRejectMessage("bob", ErrorType.REMOTE_ACTION_UNSUCCESSFUL);

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(viewManager.displayError, ErrorType.REMOTE_ACTION_UNSUCCESSFUL);//display error
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataRemoveFriendAcceptMessage() {
        System.out.println("Handle Data Server to Peer RemoveFriendAcceptMessage");
        //Arrange
        Message message = MessageFactory.createRemoveFriendAcceptMessage("bob");
        peerManager.status = StatusType.ONLINE;
        peerManager.getCurrentUser = "username";
        peerManager.getOnlineUserNames = new String[] {"a", "b", "c"};

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(peerManager.removeOnlineUser, "bob");//remove online user
        assertEquals(viewManager.setStatusCaptionStatus, peerManager.status);//update view status
        assertEquals(viewManager.setStatusCaptionUsername, peerManager.getCurrentUser);//update current user
        assertEquals(viewManager.setStatusCaptionUsers, peerManager.getOnlineUserNames);//get current users
        assertTrue(viewManager.setFocus);//set main window focus
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataRemoveFriendRejectMessage() {
        System.out.println("Handle Data Server to RemoveFriendRejectMessage");
        //Arrange
        Message message = MessageFactory.createRemoveFriendRejectMessage("bob", ErrorType.REMOTE_ACTION_UNSUCCESSFUL);
                peerManager.status = StatusType.ONLINE;
        peerManager.getCurrentUser = "username";
        peerManager.getOnlineUserNames = new String[] {"a", "b", "c"};

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(viewManager.setStatusCaptionStatus, peerManager.status);//update view status
        assertEquals(viewManager.setStatusCaptionUsername, peerManager.getCurrentUser);//update current user
        assertEquals(viewManager.setStatusCaptionUsers, peerManager.getOnlineUserNames);//get current users
        assertEquals(viewManager.displayError, ErrorType.REMOTE_ACTION_UNSUCCESSFUL);//display error
    }

    /**
     * Test of handleData method, of class PeerMessageHandler.
     */
    @Test
    public void testHandleDataChatMessage() {
        System.out.println("Handle Data Server to Peer ChatMessage");
        //Arrange
        Message message = MessageFactory.createChatMessage("bob", "alice", "inputtext");

        //Act
        instance.handleData(message.toStringArray());

        //Assert
        assertEquals(viewManager.addMessage, "(" + message.getTime() + ") " + message.getField1() + " -> " + message.getField2() + ":\n" + message.getField3());
    }
}
