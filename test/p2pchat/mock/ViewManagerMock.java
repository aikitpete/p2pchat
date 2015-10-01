/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.mock;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import p2pchat.model.ErrorType;
import p2pchat.model.StatusType;
import p2pchat.view.IViewManager;

/**
 *
 * @author USER
 */
public class ViewManagerMock implements IViewManager {

    public boolean display;
    public StatusType setStatus;
    public StatusType setStatusCaptionStatus;
    public String setStatusCaptionUsername;
    public String[] setStatusCaptionUsers;
    public String addMessage;
    public String getUserInput;
    public String setUserInput;
    public ErrorType displayError;
    public boolean setFocus;
    public String setUserImage;

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUserInput() {
        return getUserInput;
    }

    @Override
    public void addMessage(String text) {
        addMessage=text;
    }

    @Override
    public void setUserInput(String input) {
        setUserInput = input;
    }

    @Override
    public void setStatus(StatusType status) {
        setStatus = status;
    }

    @Override
    public void setStatusCaption(StatusType status, String username, String[] users) {
        setStatusCaptionStatus = status;
        setStatusCaptionUsername = username;
        setStatusCaptionUsers = users;
    }

    @Override
    public void setUserImage(String path) {
        setUserImage = path;
    }

    @Override
    public void displayAddFriend() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayRemoveFriend() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayRegister() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displaySettings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void displayLogin() {
        display = true;
    }

    @Override
    public void addDefaultListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addFeaturesListener(MouseListener mouseListener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addSubmitListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addWindowListener(WindowListener windowListener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFocus() {
        setFocus = true;;
    }

    @Override
    public void displayError(ErrorType error) {
        displayError = error;
    }

    @Override
    public void displayError(String errorCode) {
        displayError = ErrorType.valueOf(errorCode);
    }

    @Override
    public void hideAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
