package game.client;

import game.model.Player;
import game.services.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

/**
 * Created on: 22-Jun-19, 15:04
 *
 * @author: Katy Bucșa
 */

public class ControllerFXML implements IObserver {
    private IServer server;
    private Player player;
    private String currentGameIdentifier;
    private Stage currentStage;
    private List<TextField> tabla;
    @FXML
    private ComboBox<Integer> pozitia_combo;
    @FXML
    private Button start_attackBtn;
    @FXML
    private Label users, turn, altPosition;
    @FXML
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8,tf9;

    public ControllerFXML() {

    }


    public void setAttributes(Stage currentStage, IServer server, Player player) {
        this.server = server;
        this.player = player;
        this.currentStage = currentStage;
    }


    @FXML
    public void initialize() {
        pozitia_combo.setItems(FXCollections.observableList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        tabla=Arrays.asList(tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9);
    }


    @FXML
    private void logout() {
        server.logout(player, this);
        currentStage.close();
    }


    static void showMessage(String title, String type, String text) {
        Alert message;
        if ("error".equals(type))
            message = new Alert(Alert.AlertType.ERROR);
        else
            message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle(title);
        message.setContentText(text);
        message.showAndWait();
    }


    @FXML
    private void startGame() {
        if (pozitia_combo.getSelectionModel().isEmpty()) {
            showMessage("A aparut o eroare", "error", "Alegeti o pozitie!");
            return;
        }
        int poz = pozitia_combo.getSelectionModel().getSelectedItem();
        pozitia_combo.getSelectionModel().clearSelection();
        start_attackBtn.setText("Attack");
        start_attackBtn.setOnAction(x -> attack());
        start_attackBtn.setDisable(true);
        server.startGame(player, poz);
        tabla.get(poz - 1).setText("X");
    }


    private void attack() {
        if (pozitia_combo.getSelectionModel().isEmpty()) {
            showMessage("A aparut o eroare", "error", "Alegeti o pozitie!");
            return;
        }
        int poz = pozitia_combo.getSelectionModel().getSelectedItem();
        server.attack(currentGameIdentifier, player, poz);
        pozitia_combo.getSelectionModel().clearSelection();
        start_attackBtn.setDisable(true);
        turn.setText("It's the other player turn to choose a position ...");
    }

    private void newGame() {
        turn.setText("");
        users.setText("");
        altPosition.setText("");
        start_attackBtn.setText("Start");
        start_attackBtn.setOnAction(x -> startGame());
        start_attackBtn.setDisable(false);
        for (TextField tf : tabla) {
            tf.setText("");
        }
    }

    @Override
    public void update(Notification notif) throws AppException, RemoteException {
        Platform.runLater(() -> {
            if (notif.getType() == Type.WAITING_FOR_PLAYER) {
                turn.setText("Waiting for another player to get into ...");
            }
            if (notif.getType() == Type.GAME_STARTED) {
                this.currentGameIdentifier = notif.getGameIdentifier();
                users.setText(this.player.getUsername() + " - " + notif.getAdversar());

            }
            if (notif.getType() == Type.YOUR_TURN) {
                if (notif.getAdversarPoz() != 0)
                    altPosition.setText("Position tried by the other player: " + notif.getAdversarPoz());
                turn.setText("It's your turn to choose a position ...");
                start_attackBtn.setDisable(false);

            }
            if (notif.getType() == Type.OTHER_TURN) {
                turn.setText("It's the other player turn to choose a position ...");
            }

            if (notif.getType() == Type.YOU_LOST) {
                showMessage("Informare", "confirm", "Ati castigat!");
                newGame();
            }
            if (notif.getType() == Type.YOU_WON) {
                showMessage("Informare", "confirm", "Ati pierdut...incercati din nou!");
                newGame();
            }
        });
    }
}
