/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.connection.server.IConnectionManagerServer;
import p2pchat.connection.server.TransmissionRequest;
import p2pchat.mock.ConnectionManagerServerMock;
import p2pchat.mock.LoginControllerMock;
import p2pchat.mock.OnlineUserManagerServerMock;
import p2pchat.mock.RegistrationControllerMock;
import p2pchat.model.*;

/**
 *
 * @author USER
 */
public class ServerRequestHandlerTest {

    ConnectionManagerServerMock connectionManagerServer;
    RegistrationControllerMock registrationController;
    LoginControllerMock loginController;
    OnlineUserManagerServerMock onlineUserManagerServer;
    ServerRequestHandler instance;

    public ServerRequestHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        connectionManagerServer = new ConnectionManagerServerMock();
        registrationController = new RegistrationControllerMock();
        loginController = new LoginControllerMock();
        onlineUserManagerServer = new OnlineUserManagerServerMock();
        instance = new ServerRequestHandler(connectionManagerServer, registrationController, loginController,
                onlineUserManagerServer);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Add friend
     * request.
     */
    @Test
    public void testHandleDataAddFriendRequestMessage() {
        System.out.println("Handle Data AddFriendRequestMessage");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        onlineUserManagerServer.findOnlineUserUser2 = new User("bob", "localhost", "2011");
        onlineUserManagerServer.findOnlineUserUsername2 = "bob";
        Message message = MessageFactory.createAddFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 2);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "bob".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[1], MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[3], "alice".toString());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. User who sent
     * the request can not be found
     */
    @Test
    public void testHandleDataAddFriendRequestMessageRequestorNull() {
        System.out.println("Handle Data Requestor AddFriendRequestMessage Null");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser2 = new User("bob", "localhost", "2011");
        onlineUserManagerServer.findOnlineUserUsername2 = "bob";
        Message message = MessageFactory.createAddFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. User to be
     * added cant be found
     */
    @Test
    public void testHandleDataAddFriendRequestMessageAcceptorNull() {
        System.out.println("Handle Data Acceptor Null");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        Message message = MessageFactory.createAddFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Requesting user
     * added himself
     */
    @Test
    public void testHandleDataAddFriendRequestMessageSameUser() {
        System.out.println("Handle Data Acceptor Null");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        Message message = MessageFactory.createAddFriendRequestMessage("alice", "alice");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.ADD_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Logout request
     * message.
     */
    @Test
    public void testHandleDataAddLogoutRequestMessage() {
        System.out.println("Handle Data AddLogoutRequestMessage");
        //Arrange
        onlineUserManagerServer.getOnlineUsers = new User[]{new User("alice", "localhost", "2010"), new User("bob", "localhost", "2011")};

        Message message = MessageFactory.createLogoutRequestMessage("alice");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 2);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[3], "alice".toString());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Registration request message.
     */
    @Test
    public void testHandleDataRegistrationRequestMessage() {
        System.out.println("Handle Data RegistrationRequestMessage");
        //Arrange
        Message message = MessageFactory.createRegistrationRequestMessage("alice", "password", "password");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);
        registrationController.registerReturn = true;

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(registrationController.registerInput, "alice");
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REGISTRATION_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Registration
     * request message, registration rejected.
     */
    @Test
    public void testHandleDataRegistrationRequestMessageRejected() {
        System.out.println("Handle Data RegistrationRequestMessageRejected");
        //Arrange
        Message message = MessageFactory.createRegistrationRequestMessage("alice", "password", "password");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);
        registrationController.registerReturn = false;

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(registrationController.registerInput, "alice");
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REGISTRATION_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Add friend
     * request.
     */
    @Test
    public void testHandleDataRemoveFriendRequestMessage() {
        System.out.println("Handle Data RemoveFriendRequestMessage");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        onlineUserManagerServer.findOnlineUserUser2 = new User("bob", "localhost", "2011");
        onlineUserManagerServer.findOnlineUserUsername2 = "bob";
        Message message = MessageFactory.createRemoveFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 2);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "bob".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[2], MessageResult.ACCEPT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[1].getData()[3], "alice".toString());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Add friend
     * request. The user requesting to add friend not found.
     */
    @Test
    public void testHandleDataRemoveFriendRequestMessageRequestorNull() {
        System.out.println("Handle Data RemoveFriendRequestMessageRequestorNull");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser2 = new User("bob", "localhost", "2011");
        onlineUserManagerServer.findOnlineUserUsername2 = "bob";
        Message message = MessageFactory.createRemoveFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Add friend
     * request. Friend request acceptor is null.
     */
    @Test
    public void testHandleDataRemoveFriendRequestMessageAcceptorNull() {
        System.out.println("Handle Data RemoveFriendRequestMessageRequestorNull");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        Message message = MessageFactory.createRemoveFriendRequestMessage("alice", "bob");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }

    /**
     * Test of handleData method, of class ServerRequestHandler. Add friend
     * request. Requesting user and friend to be removed are same.
     */
    @Test
    public void testHandleDataRemoveFriendRequestMessageSameUser() {
        System.out.println("Handle Data RemoveFriendRequestMessageRequestorNull");
        //Arrange
        onlineUserManagerServer.findOnlineUserUser1 = new User("alice", "localhost", "2010");
        onlineUserManagerServer.findOnlineUserUsername1 = "alice";
        onlineUserManagerServer.findOnlineUserUser2 = new User("bob", "localhost", "2011");
        onlineUserManagerServer.findOnlineUserUsername2 = "bob";
        Message message = MessageFactory.createRemoveFriendRequestMessage("alice", "alice");
        TransmissionRequest request = new TransmissionRequest(2, "localhost", 2010, message);

        //Act
        instance.handleData(request);

        //Assert
        assertEquals(connectionManagerServer.sendToClientCounter, 1);
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[0], MessageType.SERVER_TO_PEER_TYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[1], MessageSubtype.REMOVE_FRIEND_RESPONSE_SUBTYPE.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[2], MessageResult.REJECT_RESULT.toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[3], "alice".toString());
        assertEquals(connectionManagerServer.sendToClientRequests[0].getData()[4], ErrorType.REMOTE_ACTION_UNSUCCESSFUL.getName());
    }
}
