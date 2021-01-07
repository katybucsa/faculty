package game.client;

import game.model.Player;
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
import java.util.List;

import static javafx.scene.paint.Color.PURPLE;

class GameView extends Stage implements Listener {
    private Controller controller;
    private MenuBar menuBar;
    private Menu menu;
    private Player player;
    private Scene scene;
    private BorderPane mainPane, leftBorderPane;
    private GridPane gridPaneR, gridPaneL;
    private ComboBox<Integer> pozitia_combo = new ComboBox<>();
    private Button start_attackBtn;
    private List<TextField> tabla;
    private Label users = new Label(), turn = new Label(), altPosition = new Label();


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
        this.setTitle("Avioane");
        mainPane = new BorderPane();
        createPaneRight();
        createPaneLeft();
        mainPane.setLeft(leftBorderPane);
        mainPane.setRight(gridPaneR);
        mainPane.setTop(createMenuBar());
        scene = new Scene(mainPane, 400, 350);
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


    private void createPaneRight() {
        gridPaneR = new GridPane();
        gridPaneR.setAlignment(Pos.CENTER);
        gridPaneR.setHgap(10);
        gridPaneR.setVgap(10);

        Text sceneTitle = new Text("Game");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);


        gridPaneR.add(sceneTitle, 1, 0, 4, 1);


        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //pozitie
        Label pozitia_label = new Label("Pozitia");
        pozitia_combo.setItems(FXCollections.observableList(l));
        gridPaneR.add(pozitia_label, 0, 5);
        gridPaneR.add(pozitia_combo, 1, 5);

        start_attackBtn = new Button("Start");
        start_attackBtn.setOnAction(x -> startGame());
        gridPaneR.add(start_attackBtn, 1, 6);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        gridPaneR.add(hBox, 0, 8);
    }


    public void createPaneLeft() {
        leftBorderPane = new BorderPane();
        GridPane gpUP = new GridPane();
        gridPaneL = new GridPane();
        gridPaneL.setAlignment(Pos.CENTER);
        tabla = new ArrayList<>();

        gpUP.add(users, 1, 2, 4, 1);
        gpUP.add(turn, 1, 3, 4, 1);
        gpUP.add(altPosition, 1, 4, 4, 1);

        int x, y = 200;
        for (int i = 1; i <= 3; i++) {
            x = 92;
            for (int j = 1; j <= 3; j++) {
                TextField tf = new TextField("");
                tf.setPrefWidth(40);
                tf.setPrefHeight(40);
                tf.setEditable(false);
                tf.setStyle("-fx-grid-lines-visible: true");
                tf.setLayoutX(x);
                tf.setLayoutY(y);
                gridPaneL.add(tf, j, i + 6, 1, 1);
                tabla.add(tf);
                x += 40;
            }
            y += 40;
        }
        leftBorderPane.setCenter(gridPaneL);
        leftBorderPane.setTop(gpUP);
    }


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
        controller.startGame(poz);
        tabla.get(poz - 1).setText("X");
    }


    private void attack() {
        if (pozitia_combo.getSelectionModel().isEmpty()) {
            showMessage("A aparut o eroare", "error", "Alegeti o pozitie!");
            return;
        }
        int poz = pozitia_combo.getSelectionModel().getSelectedItem();
        controller.attack(poz);
        pozitia_combo.getSelectionModel().clearSelection();
        start_attackBtn.setDisable(true);
        otherTurn();
    }

    @Override
    public void gameStarted(String adversar) {
        users.setText(this.player.getUsername() + " - " + adversar);
    }

    @Override
    public void waitingForPlayer() {
        turn.setText("Waiting for another player to get into ...");
    }

    @Override
    public void yourTurn(int altp) {
        if (altp != 0)
            altPosition.setText("Position tried by the other player: " + altp);
        turn.setText("It's your turn to choose a position ...");
        start_attackBtn.setDisable(false);
    }

    @Override
    public void otherTurn() {
        turn.setText("It's the other player turn to choose a position ...");
    }

    @Override
    public void gameWon() {
        showMessage("Informare", "confirm", "Ati castigat!");
        newGame();
    }

    @Override
    public void gameLost() {
        showMessage("Informare", "confirm", "Ati pierdut...incercati din nou!");
        newGame();
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
}