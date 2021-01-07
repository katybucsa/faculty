package bugs.client;

import bugs.model.Role;
import bugs.model.User;
import bugs.services.AppException;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView {
    private Controller controller;
    private Stage stage;
    private BorderPane loginPane;
    private GridPane loginGPane;
    private TextField username_field;
    private TextField password_field;
    private Button login_button;

    public LoginView(Controller c) {
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

            User user = controller.login(username, password);
            Stage userView = null;
            if (user.getRole().equals(Role.Administrator)) {
                userView = new AdministratorView(controller, user);
            }
            if (user.getRole().equals(Role.Verificator)) {
                userView = new VerificatorView(controller, user);
            }
            if (user.getRole().equals(Role.Programator)) {
                userView = new ProgramatorView(controller, user);
            }
            clearFields();
            stage.hide();
            userView.showAndWait();
            loginGPane.setDisable(false);
            stage.show();
        } catch (AppException re) {
            showErrorMessage(re.getMessage());
            loginGPane.setDisable(false);
            clearFields();
        }
    }
}
