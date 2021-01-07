package sample.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.controller.Controller;
import sample.domain.Student;
import sample.domain.Tema;
import sample.repository.RepoException;
import sample.repository.ValidationException;

import static javafx.scene.paint.Color.PURPLE;

public class AppView {
    BorderPane borderPane,notePane;
    MenuBar menuBar;
    Menu menu;
    GridPane gridPaneS, gridPaneT, mainPane;
    StackPane stackPaneS, stackPaneT;
    //student
    TextField idText, numeText, grupaText, emailText, profText;
    Button adds, deletes, updates;
    //tema
    TextField nrText, descText, deadlineText, sPText;
    Button  addt, deletet, updatet,prelung;
    Controller controller;

    private TableView<Student> tableView = new TableView<>();
    private TableView<Tema> tableViewt = new TableView<>();

    public AppView(Controller controller,BorderPane noteP) {
        this.controller = controller;
        this.notePane=noteP;
        initView();
    }

    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("A aparut o eroare");
        message.setContentText(text);
        message.showAndWait();
    }

    private void initView() {
        borderPane = new BorderPane();
        mainPane = new GridPane();
        Text sceneTitle = new Text("Catalog");
        Text title = new Text("Alegeti o optiune");
        sceneTitle.setId("sceneTitle");
        title.setId("title");
        Button studenti = new Button("Sectiunea studenti");
        studenti.setId("studenti");
        studenti.setOnAction(x -> studentInitView());
        Button teme = new Button("Sectiunea teme");
        teme.setId("teme");
        teme.setOnAction(x -> temaInitView());
        Button note = new Button("Sectiunea note");
        note.setId("note");
        note.setOnAction(x->noteInitView());
        mainPane.setId("mainPane");
        mainPane.add(sceneTitle, 1, 0, 2, 1);
        mainPane.add(title, 1, 4, 2, 1);
        mainPane.add(studenti, 1, 5);
        mainPane.add(teme, 1, 6);
        mainPane.add(note, 1, 7);
        borderPane.setCenter(mainPane);
        createTemaPane();
        createTemaTable();
        createStudentsPane();
        createStudentsTable();
    }

    private void studentInitView() {
        borderPane.setRight(gridPaneS);
        borderPane.setCenter(stackPaneS);
        borderPane.setTop(createMenuBar());
    }

    private void temaInitView() {
        borderPane.setRight(gridPaneT);
        borderPane.setCenter(stackPaneT);
        borderPane.setTop(createMenuBar());
    }

    private void noteInitView(){
        borderPane.setRight(null);
        borderPane.setCenter(notePane);
        borderPane.setTop(createMenuBar());
    }

    public BorderPane getView() {
        return borderPane;
    }

    protected void createStudentsTable() {
        stackPaneS = new StackPane();
        initStudentsView();
        stackPaneS.getChildren().add(tableView);
    }

    protected void createTemaTable() {
        stackPaneT = new StackPane();
        initTemaView();
        stackPaneT.getChildren().add(tableViewt);
    }

    private void initStudentsView() {
        TableColumn<Student, Integer> idCol = new TableColumn<>("Id");
        TableColumn<Student, String> numeCol = new TableColumn<>("Nume");
        TableColumn<Student, Integer> grupaCol = new TableColumn<>("Grupa");
        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        TableColumn<Student, String> profCol = new TableColumn<>("Profesor");


        tableView.getColumns().addAll(idCol, numeCol, grupaCol, emailCol, profCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        numeCol.setCellValueFactory(new PropertyValueFactory<>("nume"));
        grupaCol.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        profCol.setCellValueFactory(new PropertyValueFactory<>("indrumator"));


        tableView.setItems(controller.getSModel());


        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setRowFactory(tv -> changeSelection());
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStudentsDetails(newValue));
    }


    private TableRow<Student> changeSelection() {
        TableRow<Student> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty())
            {
                tableView.getSelectionModel().clearSelection();
            }
        });
        return row;
    }

    private void nullSelection() {
        deletes.setDisable(true);
        updates.setDisable(true);
        deletet.setDisable(true);
        updatet.setDisable(true);
        prelung.setDisable(true);
        clearFields();
    }

    private void showStudentsDetails(Student value) {
        if (value == null) {
            nullSelection();
            return;
        }
        deletes.setDisable(false);
        updates.setDisable(false);
        idText.setText(value.getID().toString());
        numeText.setText(value.getNume());
        grupaText.setText(String.valueOf(value.getGrupa()));
        emailText.setText(value.getEmail());
        profText.setText(value.getIndrumator());

    }

    protected void createStudentsPane() {
        gridPaneS = new GridPane();
        //gridPaneS.setId("gridPane1");
        gridPaneS.setAlignment(Pos.CENTER);
        gridPaneS.setHgap(10);
        gridPaneS.setVgap(10);
        /*gridPaneS.setPadding(new Insets(15, 15, 15, 15));
        Image im=new Image("https://unsplash.com/photos/KTdzeb28jyo",300, 350, true, true);
        Background bg=new Background(new BackgroundImage(im,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        gridPaneS.setBackground(bg);*/


        Text sceneTitle = new Text("Student");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        gridPaneS.add(sceneTitle, 1, 0, 2, 1);

        //id
        Label idLabel = new Label("Id:");
        idText = new TextField();
        gridPaneS.add(idLabel, 0, 2);
        gridPaneS.add(idText, 1, 2);
        idText.setId("id");
        idLabel.setId("idL");
        //nume
        Label numeLabel = new Label("Nume:");
        numeText = new TextField();
        gridPaneS.add(numeLabel, 0, 3);
        gridPaneS.add(numeText, 1, 3);
        numeText.setId("nume");
        numeLabel.setId("numeL");
        //grupa
        Label grupaLabel = new Label("Grupa:");
        grupaText = new TextField();
        gridPaneS.add(grupaLabel, 0, 4);
        gridPaneS.add(grupaText, 1, 4);
        grupaText.setId("grupa");
        grupaLabel.setId("grupaL");
        //email
        Label emailLabel = new Label("Email:");
        emailText = new TextField();
        gridPaneS.add(emailLabel, 0, 5);
        gridPaneS.add(emailText, 1, 5);
        emailText.setId("email");
        emailLabel.setId("emailL");
        //profesor
        Label profLabel = new Label("Indrumator");
        profText = new TextField();
        gridPaneS.add(profLabel, 0, 6);
        gridPaneS.add(profText, 1, 6);
        profText.setId("prof");
        profLabel.setId("profL");


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        adds = new Button("ADD");
        deletes = new Button("DELETE");
        updates = new Button("UPDATE");
        deletes.setDisable(true);
        updates.setDisable(true);
        adds.setId("add");
        deletes.setId("delete");
        updates.setId("update");
        adds.setOnAction(x -> addButton());
        deletes.setOnAction(x -> deleteButton());
        updates.setOnAction(x -> updateButton());
        hBox.getChildren().addAll(adds, deletes, updates);
        gridPaneS.add(hBox, 0, 8, 2, 1);
        gridPaneS.setOnMouseClicked(x -> clear());
    }

    private MenuBar createMenuBar() {
        menuBar = new MenuBar();
        menuBar.setId("menuBar");
        menu = new Menu("Optiuni");
        MenuItem m1 = new MenuItem("Studenti");
        m1.setOnAction(x -> studentInitView());
        MenuItem m2 = new MenuItem("Teme");
        m2.setOnAction(x -> temaInitView());
        MenuItem m3 = new MenuItem("Note");
        m3.setOnAction(x->noteInitView());
        menu.getItems().addAll(m1, m2, m3);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void clear() {
        tableView.getSelectionModel().clearSelection();
    }

    private void clearFields() {
        idText.setText("");
        numeText.setText("");
        grupaText.setText("");
        emailText.setText("");
        profText.setText("");
        nrText.setText("");
        descText.setText("");
        deadlineText.setText("");
        sPText.setText("");
    }

    private void addButton() {
        try {
            int id = Integer.parseInt(idText.getText());
            String nume = numeText.getText();
            int grupa = Integer.parseInt(grupaText.getText());
            String email = emailText.getText();
            String prof = profText.getText();
            controller.addStudent(id, nume, grupa, email, prof);
            clearFields();
        } catch (NumberFormatException nfe) {
            showErrorMessage("Id-ul si grupa trebuie sa fie numere intregi!" + nfe.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
    }

    private void deleteButton() {
        try {
            Student student = tableView.getSelectionModel().getSelectedItem();
            controller.deleteStudent(student);
            clearFields();
        } catch (NumberFormatException nfe) {
            showErrorMessage("Id si grupa trebuie sa fie numere!" + nfe.getMessage());
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
    }

    private void updateButton() {
        Student student = tableView.getSelectionModel().getSelectedItem();
        try {
            int id = Integer.parseInt(idText.getText());
            String nume = numeText.getText();
            int grupa = Integer.parseInt(grupaText.getText());
            String email = emailText.getText();
            String prof = profText.getText();
            controller.updateStudent(student, id, nume, grupa, email, prof);
            clearFields();
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
    }


    //*************************Tema***************************

    private void initTemaView() {
        TableColumn<Tema, Integer> idCol = new TableColumn<>("Nr. tema");
        TableColumn<Tema, String> descCol = new TableColumn<>("Descriere");
        TableColumn<Tema, Integer> deadlineCol = new TableColumn<>("Deadline");
        TableColumn<Tema, Integer> saptPrimireCol = new TableColumn<>("Sapt. primire tema");

        tableViewt.getColumns().addAll(idCol, descCol, deadlineCol, saptPrimireCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        saptPrimireCol.setCellValueFactory(new PropertyValueFactory<>("saptPrimireTema"));

        tableViewt.setItems(controller.getTModel());

        tableViewt.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewt.setRowFactory(tv -> changeSelectionT());
        tableViewt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTemaDetails(newValue));
    }

    private TableRow<Tema> changeSelectionT() {
        TableRow<Tema> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty())
                tableViewt.getSelectionModel().clearSelection();
        });
        return row;
    }


    private void showTemaDetails(Tema value) {
        if (value == null) {
            nullSelection();
            return;
        }
        deletet.setDisable(false);
        updatet.setDisable(false);
        prelung.setDisable(false);
        nrText.setText(value.getID().toString());
        descText.setText(value.getDescriere());
        deadlineText.setText(value.getDeadline().toString());
        sPText.setText(value.getSaptPrimireTema().toString());
    }

    protected void createTemaPane() {
        gridPaneT = new GridPane();
        //gridPaneT.setId("gridPane");
        gridPaneT.setAlignment(Pos.CENTER);
        gridPaneT.setHgap(10);
        gridPaneT.setVgap(10);

        Text sceneTitle = new Text("Teme");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        gridPaneT.add(sceneTitle, 1, 0, 2, 1);

        //nrTema
        Label nrLabel = new Label("Numar");
        nrText = new TextField();
        gridPaneT.add(nrLabel, 0, 2);
        gridPaneT.add(nrText, 1, 2);
        nrText.setId("nr");
        nrLabel.setId("nrL");
        //descriere
        Label descLabel = new Label("Descriere");
        descText = new TextField();
        gridPaneT.add(descLabel, 0, 3);
        gridPaneT.add(descText, 1, 3);
        descText.setId("desc");
        descLabel.setId("descL");
        //deadline
        Label deadlineLabel = new Label("Deadline");
        deadlineText = new TextField();
        gridPaneT.add(deadlineLabel, 0, 4);
        gridPaneT.add(deadlineText, 1, 4);
        deadlineText.setId("deadline");
        deadlineLabel.setId("deadlineL");
        //saptamanaPrimireTema
        Label sPLabel = new Label("Saptamana primire");
        sPText = new TextField();
        gridPaneT.add(sPLabel, 0, 5);
        gridPaneT.add(sPText, 1, 5);
        sPText.setId("sPT");
        sPLabel.setId("sPTL");

        addt = new Button("ADD");
        deletet = new Button("DELETE");
        updatet = new Button("UPDATE");
        prelung=new Button("PRELUNG");
        deletet.setDisable(true);
        updatet.setDisable(true);
        prelung.setDisable(true);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        addt.setId("add");
        deletet.setId("delete");
        updatet.setId("update");
        prelung.setId("prelung");
        addt.setOnAction(x -> addButtonT());
        deletet.setOnAction(x -> deleteButtonT());
        updatet.setOnAction(x -> updateButtonT());
        prelung.setOnAction(x->prelungTermen());
        hBox.getChildren().addAll(addt, deletet, updatet,prelung);
        gridPaneT.add(hBox, 0, 7, 2, 1);
        gridPaneT.setOnMouseClicked(x -> clearT());
    }

    private void clearT() {
        tableViewt.getSelectionModel().clearSelection();
    }

    private void addButtonT() {
        try {
            int nr = Integer.parseInt(nrText.getText());
            String desc = descText.getText();
            int deadline = Integer.parseInt(deadlineText.getText());
            int sPT = Integer.parseInt(sPText.getText());
            controller.addTema(nr, desc, deadline, sPT);
            clearFields();
        } catch (NumberFormatException nfe) {
            showErrorMessage("Numarul temei, deadline si saptamana primire trenuie safie numere!");
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
        clearFields();
    }

    private void deleteButtonT() {
        try {
            Tema tema = tableViewt.getSelectionModel().getSelectedItem();
            controller.deleteTema(tema);
            clearFields();
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
        clearFields();
    }

    private void prelungTermen(){
        try{
            Tema t=tableViewt.getSelectionModel().getSelectedItem();
            controller.prelungesteTermen(t);
            clearFields();
        }catch (NumberFormatException nfe){
            showErrorMessage(nfe.getMessage());
        }
        catch (IllegalArgumentException iae){
            showErrorMessage(iae.getMessage());
        }
        catch (ValidationException ve){
            showErrorMessage(ve.getMessage());
        }
        clearFields();
    }

    private void updateButtonT() {
        Tema tema = tableViewt.getSelectionModel().getSelectedItem();
        try {
            int nr = Integer.parseInt(nrText.getText());
            String desc = descText.getText();
            int deadline = Integer.parseInt(deadlineText.getText());
            int sPT = Integer.parseInt(sPText.getText());
            controller.updateTema(tema, nr, desc, deadline, sPT);
            clearFields();
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ValidationException ve) {
            showErrorMessage(ve.getMessage());
        } catch (RepoException re) {
            showErrorMessage(re.getMessage());
        }
        clearFields();
    }
}