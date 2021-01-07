package view;

import controller.Controller;
import domain.Ride;
import domain.User;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.RepositoryException;
import repository.ValidationException;
import utils.RBooking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.PURPLE;


class RideView extends Stage {
    private Controller controller;
    private MenuBar menuBar;
    private Menu menu;
    private User user;
    private Scene scene;
    private BorderPane mainPane;
    private GridPane gridPaneR;
    private StackPane stackPaneR;
    private TextField destination_field, date_field, hour_field, client_field;
    private Button search, addB;
    private ComboBox<Integer> combo_places=new ComboBox<>();

    private TableView<Ride> tableViewR = new TableView<>();



    public RideView(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        initView();
    }

    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("A aparut o eroare");
        message.setContentText(text);
        message.showAndWait();
    }


    private void initView() {
        mainPane = new BorderPane();
        createRidePane();
        createRideTable();
        mainPane.setLeft(stackPaneR);
        mainPane.setRight(gridPaneR);
        mainPane.setTop(createMenuBar());
        scene = new Scene(mainPane, 700, 600);
        this.setScene(scene);
//      scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

    private MenuBar createMenuBar(){
        menuBar=new MenuBar();
        menuBar.setId("menuBar");
        menu=new Menu("File");
        MenuItem m=new MenuItem("Logout");
        m.setOnAction(x->this.close());
        menu.getItems().add(m);
        menuBar.getMenus().add(menu);
        return menuBar;

    }

    private Stage getStage(String dest, String date, String hour) {
        Stage s = new Stage();
        BorderPane rbPane = new BorderPane();
        StackPane stackPaneRB = new StackPane();
        TableView<RBooking> tableViewRB = new TableView<>();

        TableColumn<RBooking, Integer> placeCol = new TableColumn<>("Loc");
        TableColumn<RBooking, String> nameCol = new TableColumn<>("Nume");
        tableViewRB.getColumns().addAll(placeCol, nameCol);


        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        tableViewRB.setItems(controller.getRBModel(dest, date, hour));
        tableViewRB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        stackPaneRB.getChildren().add(tableViewRB);
        rbPane.setCenter(stackPaneRB);
        Scene sc = new Scene(rbPane, 400, 400);
        s.setScene(sc);
        return s;
    }

    private void searchRide() {
        String destination = destination_field.getText();
        String date = date_field.getText();
        String hour = hour_field.getText();
        try {
            getStage(destination, date, hour).show();
        } catch (RepositoryException re) {
            showErrorMessage(re.getMessage());
        }
    }

    private void createRidePane() {
        gridPaneR = new GridPane();
        gridPaneR.setAlignment(Pos.CENTER);
        gridPaneR.setHgap(10);
        gridPaneR.setVgap(10);

        Text sceneTitle = new Text("Curse");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        gridPaneR.add(sceneTitle, 1, 0, 2, 1);

        //destination
        Label destination_label = new Label("Destinația");
        destination_field = new TextField();
        gridPaneR.add(destination_label, 0, 2);
        gridPaneR.add(destination_field, 1, 2);

        //date
        Label date_label = new Label("   Data");
        date_field = new TextField();
        gridPaneR.add(date_label, 0, 3);
        gridPaneR.add(date_field, 1, 3);

        //hour
        Label hour_label = new Label("   Ora");
        hour_field = new TextField();
        hour_field.prefWidth(10);
        gridPaneR.add(hour_label, 0, 4);
        gridPaneR.add(hour_field, 1, 4);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        search = new Button("Caută");
        search.setOnAction(x -> searchRide());
        hBox.getChildren().add(search);

        gridPaneR.add(hBox, 0, 8, 2, 1);

        gridPaneR.setOnMouseClicked(x -> clear());
    }


    public void clear() {
        tableViewR.getSelectionModel().clearSelection();
    }

    public void createRideTable() {
        TableColumn<Ride, String> destinationCol = new TableColumn<>("Destinatia");
        TableColumn<Ride, String> dateCol = new TableColumn<>("Data");
        TableColumn<Ride, String> hourCol = new TableColumn<>("Hour");
        TableColumn<Ride, Integer> nrplacesCol = new TableColumn<>("Nr. locuri disponibile");
        tableViewR.getColumns().addAll(destinationCol, dateCol, hourCol, nrplacesCol);

        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
        nrplacesCol.setCellValueFactory(new PropertyValueFactory<>("nrPlacesAvailable"));

        tableViewR.setItems(controller.getRModel());

        tableViewR.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewR.setRowFactory(x -> changeSelection());
        tableViewR.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showRideDetailes(newValue)));

        stackPaneR = new StackPane();

        Label client = new Label("Client ");
        client_field = new TextField();
        Label places = new Label("Nr. locuri ");
        addB = new Button("Rezervă");
        addB.setOnAction(x -> addBooking());
        addB.setDisable(true);


        GridPane gp=new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPrefHeight(100);
        BorderPane bp = new BorderPane();
        gp.add(client,0,1,1,1);
        gp.add(places,3,1,1,1);
        gp.add(client_field,0,2,1,1);
        gp.add(combo_places,3,2,1,1);
        gp.add(addB,4,2,1,1);
        bp.setTop(gp);
        tableViewR.setPrefWidth(420);
        bp.setCenter(tableViewR);
        stackPaneR.getChildren().add(bp);
    }

    private void addBooking(){
        String cname=client_field.getText();
        if(cname.isEmpty()){
            showErrorMessage("Nume invalid!");
            return;
        }
        if(combo_places.getSelectionModel().isEmpty()){
            showErrorMessage("Selectati numarul de locuri dorite!");
            return;
        }
        int nrplaces = combo_places.getSelectionModel().getSelectedItem();
        try{
            Ride ride=tableViewR.getSelectionModel().getSelectedItem();
            System.out.println(ride.getID());
            String msg=controller.bookPlaces(ride,cname,nrplaces);
            Alert info=new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Rezervare");
            info.setContentText("Locuri rezervate: "+ msg);
            info.showAndWait();
        }catch (RepositoryException re){
            showErrorMessage(re.getMessage());
        }
    }

    private TableRow<Ride> changeSelection() {
        TableRow<Ride> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty()) {
                tableViewR.getSelectionModel().clearSelection();
                combo_places.getItems().clear();
            }
        });
        return row;
    }

    private void showRideDetailes(Ride ride) {
        if (ride == null) {
            combo_places.getItems().clear();
            nullSelection();
            addB.setDisable(true);
            return;
        }
        addB.setDisable(false);
        destination_field.setText(ride.getDestination());
        date_field.setText(ride.getDate());
        hour_field.setText(ride.getHour());
        List<Integer> l=new ArrayList<>();
        for(int i=1;i<=ride.getNrPlacesAvailable();i++)
            l.add(i);
        combo_places.setItems(FXCollections.observableArrayList(l));
    }

    private void nullSelection() {
        clearFields();
    }


    private void clearFields() {
        destination_field.setText("");
        date_field.setText("");
        hour_field.setText("");
    }
}


public class AppView {
    private Controller controller;
    private Stage stage;
    private BorderPane loginPane;
    private GridPane loginGPane;
    private TextField username_field;
    private TextField password_field;
    private Button login_button;

    public AppView(Controller c) {
        this.controller = c;
        initView();
    }

    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("A aparut o eroare");
        message.setContentText(text);
        message.showAndWait();
    }

    private void initView() {
        loginPane = new BorderPane();
        loginPane.setId("loginBPane");
        createLoginPane();
        loginPane.setCenter(loginGPane);
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.show();
    }

    public BorderPane getView() {
        return loginPane;
    }

    private void createLoginPane() {
        loginGPane = new GridPane();
        loginGPane.setId("login_pane");
        Text title = new Text("Autentificare");
        title.setId("login_title");
        //username
        Label username_label = new Label("Username  ");
        username_label.setId("username_label");
        username_field = new TextField();
        username_field.setId("username_field");
        //password
        Label password_label = new Label("Password  ");
        password_label.setId("password_label");
        password_field = new PasswordField();
        password_field.setId("password_field");

        //login
        login_button = new Button("Login");
        login_button.setId("login");
        login_button.setOnAction(x -> login());
        loginGPane.add(title, 3, 0, 1, 1);
        loginGPane.add(username_label, 1, 4, 2, 1);
        loginGPane.add(username_field, 3, 4, 1, 1);
        loginGPane.add(password_label, 1, 6, 2, 1);
        loginGPane.add(password_field, 3, 6, 1, 1);
        loginGPane.add(login_button, 3, 8, 1, 1);
    }

    private void clearFields() {
        username_field.setText("");
        password_field.setText("");
    }

    private void login() {
        String username = username_field.getText();
        String password = password_field.getText();
        ProgressIndicator prog = new ProgressIndicator();
        VBox vBox = new VBox(prog);
        vBox.setAlignment(Pos.CENTER);
        try {
            loginGPane.setDisable(true);
            loginPane.getChildren().add(vBox);

            controller.findUser(username, password);
            User user = new User(username, password, "abc");
            RideView rv = new RideView(controller, user);
            clearFields();
            stage.hide();
            rv.showAndWait();
            loginGPane.setDisable(false);
            stage.show();
            //stage.close();
        } catch (RepositoryException re) {
            showErrorMessage(re.getMessage());
            loginGPane.setDisable(false);
            clearFields();
        }catch(ValidationException ve) {
            showErrorMessage(ve.getMessage());
            loginGPane.setDisable(false);
            clearFields();
        }
    }
}