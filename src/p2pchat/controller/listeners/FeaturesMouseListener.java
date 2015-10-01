package p2pchat.controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import p2pchat.controller.P2PChatController;
import p2pchat.view.ButtonType;
import p2pchat.view.custom.IUpdatable;

/**
 * Reset content of input field
 *
 */
public class FeaturesMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        IUpdatable source = (IUpdatable) e.getSource();

        switch (source.getButtonType()) {
            case HISTORY:
                historyActionPerformed();
                break;
            case REGISTER:
                registerActionPerformed();
                break;
            case ADD_FRIEND:
                friendActionPerformed();
                break;
            case SETTINGS:
                settingsActionPerformed();
                break;
            case PROFILE:
                profileActionPerformed();
                break;
        }
    }

    private void historyActionPerformed() {
        if (P2PChatController.getModel().isOnline()) {
            P2PChatController.getView().displayHistory();
        }
    }

    private void registerActionPerformed() {
        P2PChatController.getView().displayRegister();
    }

    private void friendActionPerformed() {
        if (P2PChatController.getModel().isOnline()) {
            P2PChatController.getView().addMessage(P2PChatController.getModel().getText());
            P2PChatController.getView().displayFriend();
        }
    }

    private void settingsActionPerformed() {
        P2PChatController.getView().displaySettings();
    }

    private void profileActionPerformed() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        IUpdatable icon = (IUpdatable) e.getSource();
        icon.setActive(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        IUpdatable icon = (IUpdatable) e.getSource();
        icon.setActive(false);
    }
}
