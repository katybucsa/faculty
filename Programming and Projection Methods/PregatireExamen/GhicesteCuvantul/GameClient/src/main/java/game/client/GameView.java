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
    private ComboBox<String> litera_combo = new ComboBox<>();
    private Button start_attackBtn;
    private TextField cuv_field;
    private Label cuv_label;
    private ListView<String> listView;
    private Label turn = new Label();


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
        mainPane.setCenter(leftBorderPane);
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


    public void createPaneLeft() {
        GridPane gpUP = new GridPane();
        gpUP.add(turn, 1, 3, 4, 1);
        listView = new ListView<>();

        leftBorderPane = new BorderPane();
        leftBorderPane.setCenter(listView);
        leftBorderPane.setTop(gpUP);
    }

    private void createPaneRight() {
        gridPaneR = new GridPane();
        gridPaneR.setAlignment(Pos.CENTER);
        gridPaneR.setHgap(10);
        gridPaneR.setVgap(10);

        Text sceneTitle = new Text("Ghiceste cuvantul");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);


        gridPaneR.add(sceneTitle, 1, 0, 4, 1);


        //pozitie
        cuv_label = new Label("Cuvant");
        cuv_label.setVisible(false);
        cuv_field = new TextField();
        cuv_field.setPrefColumnCount(3);
        cuv_field.setVisible(false);
        gridPaneR.add(cuv_label, 0, 5);
        gridPaneR.add(cuv_field, 1, 5);

//        pozitia_combo.setItems(FXCollections.observableList(l));
//        poz_combo.setItems(FXCollections.observableList(l));
//        gridPaneR.add(pozitia_combo, 1, 5);
//        gridPaneR.add(poz_combo, 3, 5);

        start_attackBtn = new Button("Start");
        start_attackBtn.setOnAction(x -> startGame());
        gridPaneR.add(start_attackBtn, 1, 6);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        gridPaneR.add(hBox, 0, 8);
    }

    private void startChooseWords() {
        if (!cuv_field.getText().matches("^[a-z][a-z][a-z]$")) {
            showMessage("Alegeti un cuvant", "error", "Alegeti un cuvant inainte");
            return;
        }
        String word = cuv_field.getText();
        controller.startChooseWords(word);
        cuv_field.clear();
        start_attackBtn.setText("Ataca");
        start_attackBtn.setOnAction(x -> attack());
        start_attackBtn.setDisable(true);
    }


    private void startGame() {
        try {
            controller.startGame();
        } catch (AppException ae) {
            showMessage("Eroare la inceperea jocului", "error", ae.getMessage());
        }
    }


    private void attack() {
        if (litera_combo.getSelectionModel().isEmpty() || listView.getSelectionModel().isEmpty()) {
            showMessage("A aparut o eroare", "error", "Alegeti un cuvant si o litera!");
            return;
        }
        String litera = litera_combo.getSelectionModel().getSelectedItem();
        String[] l = listView.getSelectionModel().getSelectedItem().split(":");
        String user_cuv = l[0];

        controller.attack(user_cuv, litera);
        litera_combo.getSelectionModel().clearSelection();
        listView.getSelectionModel().clearSelection();
        start_attackBtn.setDisable(true);
        turn.setText("It's the other player turn to choose a position ...");
    }

    @Override
    public void gameStarted(List<String> player_word) {
        listView.getItems().clear();
        listView.setItems(FXCollections.observableList(player_word));
        cuv_label.setText("Litera");
        gridPaneR.getChildren().remove(cuv_field);
        litera_combo.setItems(FXCollections.observableList(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")));
        gridPaneR.add(litera_combo, 1, 5);
    }

    @Override
    public void yourTurn() {
        turn.setText("It's your turn to choose a position ...");
        start_attackBtn.setDisable(false);
    }

    @Override
    public void gameOver(List<String> l) {
        String str = "";
        for (int i = 0; i < l.size(); i++)
            str += l.get(i) + "\n";
        showMessage("Jocul s-a incheiat", "confirm", str);
        newGame();
    }


    @Override
    public void chooseWord(List<String> l) {
        listView.setItems(FXCollections.observableList(l));
        cuv_field.setVisible(true);
        cuv_label.setVisible(true);
        start_attackBtn.setText("Alege");
        start_attackBtn.setOnAction(x -> startChooseWords());
        start_attackBtn.setDisable(false);
    }

    @Override
    public void letterGuessed(List<String> l) {
        listView.setItems(FXCollections.observableList(l));
    }

    private void newGame() {
        turn.setText("");
        start_attackBtn.setText("Start");
        start_attackBtn.setOnAction(x -> startGame());
        start_attackBtn.setDisable(false);
        gridPaneR.getChildren().remove(litera_combo);
        gridPaneR.add(cuv_field, 1, 5);
        cuv_field.setVisible(false);
        cuv_label.setText("Cuvant");
        cuv_label.setVisible(false);
        listView.getItems().clear();
        listView.setVisible(false);
    }
}