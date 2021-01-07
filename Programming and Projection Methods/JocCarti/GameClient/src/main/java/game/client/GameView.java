package game.client;

import game.model.Player;
import game.services.AppException;
import game.services.Listener;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javafx.scene.paint.Color.PURPLE;

class GameView extends Stage implements Listener {
    private Controller controller;
    private MenuBar menuBar;
    private Menu menu;
    private Player player;
    private Scene scene;
    private BorderPane mainPane, leftBorderPane;
    private GridPane gridPaneR;
    private Button start_attackBtn;
    private ListView<String> listView, listViewCarti;
    private Label turn = new Label(), waiting = new Label();


    public GameView(Controller controller, Player player) {
        this.controller = controller;
        this.player = player;
        this.controller.setListener(this);
        initView();
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


    private void initView() {
        this.setTitle("Carti");
        mainPane = new BorderPane();
        createPaneRight();
        createPaneLeft();
        mainPane.setCenter(leftBorderPane);
        mainPane.setRight(gridPaneR);
        mainPane.setTop(createMenuBar());
        scene = new Scene(mainPane, 600, 350);
        this.setScene(scene);
    }

    private MenuBar createMenuBar() {
        menuBar = new MenuBar();
        menuBar.setId("menuBar");
        menu = new Menu("File");
        MenuItem m = new MenuItem("Logout");
        m.setOnAction(x -> logout());
        menu.getItems().add(m);
        menuBar.getMenus().add(menu);
        return menuBar;

    }

    private void logout() {
        controller.logout(player);
        this.close();
    }


    public void createPaneLeft() {
        GridPane gpUP = new GridPane();
        gpUP.add(turn, 1, 3, 4, 1);
        gpUP.add(waiting, 1, 3, 4, 1);
        listView = new ListView<>();
        listViewCarti = new ListView<>();

        leftBorderPane = new BorderPane();
        leftBorderPane.setLeft(listView);
        leftBorderPane.setCenter(listViewCarti);
        leftBorderPane.setTop(gpUP);
    }

    private void createPaneRight() {
        gridPaneR = new GridPane();
        gridPaneR.setAlignment(Pos.CENTER);
        gridPaneR.setHgap(10);
        gridPaneR.setVgap(10);

        Text sceneTitle = new Text("Joc carti");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);


        gridPaneR.add(sceneTitle, 1, 0, 4, 1);

        start_attackBtn = new Button("Start");
        start_attackBtn.setOnAction(x -> startGame());
        gridPaneR.add(start_attackBtn, 1, 3);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        gridPaneR.add(hBox, 0, 8);
    }


    private void startGame() {
        try {
            controller.startGame();
            start_attackBtn.setText("Alege");
            start_attackBtn.setDisable(true);
            start_attackBtn.setOnAction(x -> attack());
            waiting.setText("Waiting for players...");
        } catch (AppException ae) {
            showMessage("Eroare la inceperea jocului", "error", ae.getMessage());
        }
    }


    private void attack() {
        if (listViewCarti.getSelectionModel().isEmpty()) {
            showMessage("A aparut o eroare", "error", "Alegeti o carte!");
            return;
        }
        String carte = listViewCarti.getSelectionModel().getSelectedItem();
        listViewCarti.getItems().remove(carte);
        controller.attack(carte);
        listViewCarti.getSelectionModel().clearSelection();
        start_attackBtn.setDisable(true);
        turn.setText("Este randul altui jucator ...");
    }

    @Override
    public void gameStarted(List<String> participants, List<String> carti) {
        waiting.setVisible(false);
        listView.setItems(FXCollections.observableList(participants));
        listViewCarti.setItems(FXCollections.observableList(carti));
    }

    @Override
    public void yourTurn() {
        turn.setText("E randul tau sa alegi o carte ...");
        start_attackBtn.setDisable(false);
    }

    @Override
    public void gameOver(String winner) {
        showMessage("Jocul s-a incheiat", "confirm", "Castigator: " + winner);
        newGame();
    }

    @Override
    public void cartePrimita(List<String> carti) {
        listViewCarti.setItems(FXCollections.observableList(carti));
        start_attackBtn.setDisable(false);
    }


    private void newGame() {
        turn.setText("");
        start_attackBtn.setText("Start");
        start_attackBtn.setOnAction(x -> startGame());
        start_attackBtn.setDisable(false);

        listView.getItems().clear();
        listViewCarti.getItems().clear();
//        listView.setVisible(false);
//        listViewCarti.setVisible(false);
    }
}