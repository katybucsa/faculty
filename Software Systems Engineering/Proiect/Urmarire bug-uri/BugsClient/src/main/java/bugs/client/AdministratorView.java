package bugs.client;


import bugs.model.Role;
import bugs.model.User;
import bugs.services.AppException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.PURPLE;

public class AdministratorView extends Stage {
    private Controller controller;
    private User user;
    private Scene scene;
    private BorderPane mainPane;
    private GridPane gridPaneA;
    private StackPane stackPaneA;
    private TextField username_field, password_field, fName_field, lName_field;
    private ToggleGroup choiceRole = new ToggleGroup();
    private RadioButton verifBtn, progBtn, adminBtn;
    private Button addBtn, updateBtn, deleteBtn;
    private TableView<User> adminTableView = new TableView<>();


    public AdministratorView(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        initView();
    }

    static void showMessage(String title, String type, String text) {
        Alert message;
        if ("error".equals(type))
            message = new Alert(Alert.AlertType.ERROR);
        else
            message = new Alert(Alert.AlertType.CONFIRMATION);
        message.setTitle(title);
        message.setContentText(text);
        message.showAndWait();
    }


    private void initView() {
        this.setTitle("Urmarire bug-uri");
        mainPane = new BorderPane();
        createAdminPane();
        createAdminTable();
        mainPane.setLeft(stackPaneA);
        mainPane.setRight(gridPaneA);
        scene = new Scene(mainPane, 700, 600);
        this.setScene(scene);
    }

    private void createAdminPane() {
        gridPaneA = new GridPane();
        gridPaneA.setAlignment(Pos.CENTER);
        gridPaneA.setHgap(10);
        gridPaneA.setVgap(10);

        Text sceneTitle = new Text("Useri");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        gridPaneA.add(sceneTitle, 1, 0, 2, 1);

        //username
        Label username_label = new Label("Username");
        username_field = new TextField();
        gridPaneA.add(username_label, 0, 2);
        gridPaneA.add(username_field, 1, 2);

        //password
        Label password_label = new Label("Parola");
        password_field = new TextField();
        gridPaneA.add(password_label, 0, 3);
        gridPaneA.add(password_field, 1, 3);

        //fName
        Label fName_label = new Label("Prenume");
        fName_field = new TextField();
        fName_field.prefWidth(10);
        gridPaneA.add(fName_label, 0, 4);
        gridPaneA.add(fName_field, 1, 4);

        //lName
        Label lName_label = new Label("Nume");
        lName_field = new TextField();
        lName_field.prefWidth(10);
        gridPaneA.add(lName_label, 0, 5);
        gridPaneA.add(lName_field, 1, 5);

        verifBtn = new RadioButton("Verificator");
        verifBtn.setToggleGroup(choiceRole);
        verifBtn.setSelected(true);
        verifBtn.setUserData(Role.Verificator);
        progBtn = new RadioButton("Programator");
        progBtn.setToggleGroup(choiceRole);
        progBtn.setUserData(Role.Programator);
        adminBtn = new RadioButton("Administrator");
        adminBtn.setToggleGroup(choiceRole);
        adminBtn.setUserData(Role.Administrator);
        gridPaneA.add(verifBtn, 1, 7);
        gridPaneA.add(progBtn, 1, 8);
        gridPaneA.add(adminBtn, 1, 9);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        addBtn = new Button("Adaugă");
        addBtn.setOnAction(x -> addUser());
        updateBtn = new Button("Modifică");
        updateBtn.setOnAction(x -> updateUser());
        updateBtn.setDisable(true);
        deleteBtn = new Button("Șterge");
        deleteBtn.setOnAction(x -> deleteUser());
        deleteBtn.setDisable(true);
        hBox.getChildren().add(addBtn);
        hBox.getChildren().add(updateBtn);
        hBox.getChildren().add(deleteBtn);

        gridPaneA.add(hBox, 0, 14, 2, 1);

        gridPaneA.setOnMouseClicked(x -> clear());
    }

    public void createAdminTable() {
        TableColumn<User, String> lNameCol = new TableColumn<>("Nume");
        TableColumn<User, String> fNameCol = new TableColumn<>("Prenume");
        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        TableColumn<User, Role> roleCol = new TableColumn<>("Rol");
        adminTableView.getColumns().addAll(fNameCol, lNameCol, usernameCol, roleCol);

        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        adminTableView.setItems(controller.getUserModel());

        adminTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        adminTableView.setRowFactory(x -> changeSelection());
        adminTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showUserDetailes(newValue)));

        stackPaneA = new StackPane();

        Text sceneTitle = new Text("Lista useri");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPrefHeight(70);
        BorderPane bp = new BorderPane();
        gp.add(sceneTitle, 3, 2, 1, 1);

        bp.setTop(gp);
        adminTableView.setPrefWidth(420);
        bp.setCenter(adminTableView);
        stackPaneA.getChildren().add(bp);
    }


    private TableRow<User> changeSelection() {
        TableRow<User> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty()) {
                adminTableView.getSelectionModel().clearSelection();
            }
        });
        return row;
    }

    private void showUserDetailes(User user) {
        if (user == null) {
            nullSelection();
            //addBtn.setDisable(true);
            updateBtn.setDisable(true);
            deleteBtn.setDisable(true);
            username_field.setEditable(true);
            return;
        }
        //addBtn.setDisable(false);
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        username_field.setText(user.getID());
        password_field.setText(user.getPassword());
        fName_field.setText(user.getFirstName());
        lName_field.setText(user.getLastName());
        username_field.setEditable(false);
        if (user.getRole().equals(Role.Programator))
            progBtn.setSelected(true);
        if (user.getRole().equals(Role.Verificator))
            verifBtn.setSelected(true);
    }


    private void addUser() {
        String username = username_field.getText();
        String pass = password_field.getText();
        String firstName = fName_field.getText();
        String lastName = lName_field.getText();
        Role role = (Role) choiceRole.getSelectedToggle().getUserData();
        if ("".equals(username) || "".equals(pass) || "".equals(firstName) || "".equals(lastName)) {
            showMessage("A aparut o eroare", "error", "Campurile trebuie completate!");
            return;
        }
        try {
            controller.addUser(username, pass, firstName, lastName, role);
            clearFields();
            showMessage("Adaugare user", "confirm", "Adaugarea a fost realizata cu succes");
        } catch (AppException ex) {
            showMessage("A aparut o eroare in timpul adaugarii", "error", ex.getMessage());
        }
    }

    private void updateUser() {
        String username, password, fName, lName;
        Role role;
        username = username_field.getText();
        password = password_field.getText();
        fName = fName_field.getText();
        lName = lName_field.getText();
        role = (Role) choiceRole.getSelectedToggle().getUserData();
        if ("".equals(username) || "".equals(password) || "".equals(fName) || "".equals(lName)) {
            showMessage("A aparut o eroare", "error", "Campuri necompletate!\nVa rugam sa completati campurile");
            return;
        }
        try {
            controller.updateUser(username, password, role, fName, lName);
            clearFields();
            showMessage("Modificare user", "confirm", "Utilizatorul a fost modificat cu succes");
        } catch (AppException ex) {
            System.out.println(ex.getMessage());
            showMessage("A aparut o eroare", "error", ex.getMessage());
        }
    }

    private void deleteUser() {
        String userId = adminTableView.getSelectionModel().getSelectedItem().getID();
        try {
            controller.deleteUser(userId);
            clearFields();
        } catch (AppException ex) {
            showMessage("A aparut o eroare", "error", ex.getMessage());
        }
    }


    public void clear() {
        adminTableView.getSelectionModel().clearSelection();
    }

    private void nullSelection() {
        clearFields();
    }


    private void clearFields() {
        username_field.setText("");
        password_field.setText("");
        fName_field.setText("");
        lName_field.setText("");
        verifBtn.setSelected(true);
    }

}
