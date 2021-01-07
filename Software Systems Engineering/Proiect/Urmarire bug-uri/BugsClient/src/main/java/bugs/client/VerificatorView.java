package bugs.client;


import bugs.model.Bug;
import bugs.model.User;
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

import java.util.List;
import java.util.Optional;

import static javafx.scene.paint.Color.PURPLE;

public class VerificatorView extends Stage {
    private Controller controller;
    private User user;

    private Scene scene;
    private BorderPane mainPane;
    private GridPane gridPaneT;
    private StackPane stackPaneT;
    private TextField naming_field;
    private TextArea description_area;
    private Button addBtn, asignBtn, viewSolvedBtn, testBtn;
    private TableView<Bug> testerTableView = new TableView<>();
    private TableView<Bug> tableViewSB;


    public VerificatorView(Controller controller, User user) {
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
        this.setTitle("Urmărire bug-uri");
        mainPane = new BorderPane();
        createTesterPane();
        createTesterTable();
        mainPane.setLeft(stackPaneT);
        mainPane.setRight(gridPaneT);
        scene = new Scene(mainPane, 800, 600);
        this.setScene(scene);
    }


    private void createTesterPane() {
        gridPaneT = new GridPane();
        gridPaneT.setAlignment(Pos.CENTER);
        gridPaneT.setHgap(10);
        gridPaneT.setVgap(10);

        Text sceneTitle = new Text("Bug-uri");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        gridPaneT.add(sceneTitle, 1, 0, 2, 1);

        //naming
        Label naming_label = new Label("Denumire");
        naming_field = new TextField();
        gridPaneT.add(naming_label, 0, 2);
        gridPaneT.add(naming_field, 1, 2);

        //description
        Label description_label = new Label("Descriere");
        description_area = new TextArea();
        description_area.setPrefWidth(250);
        description_area.setWrapText(true);
        gridPaneT.add(description_label, 0, 3);
        gridPaneT.add(description_area, 1, 3);


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        addBtn = new Button("Adaugă");
        addBtn.setOnAction(x -> addBug());
        hBox.getChildren().add(addBtn);

        gridPaneT.add(hBox, 0, 8, 2, 1);

        gridPaneT.setOnMouseClicked(x -> clear());
    }


    private void addBug() {
        String naming = naming_field.getText();
        String description = description_area.getText();
        controller.addBug(naming, description);
        showMessage("Confirmare", "Confirm", "Bug-ul a fost adaugat cu succes!");
        clearFields();
    }


    public void clear() {
        testerTableView.getSelectionModel().clearSelection();
    }

    public void createTesterTable() {
        TableColumn<Bug, String> namingCol = new TableColumn<>("Denumire");
        TableColumn<Bug, String> descriptionCol = new TableColumn<>("Descriere");
        TableColumn<Bug, Stage> stageCol = new TableColumn<>("Stadiu");
        testerTableView.getColumns().addAll(namingCol, descriptionCol, stageCol);

        namingCol.setCellValueFactory(new PropertyValueFactory<>("naming"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        stageCol.setCellValueFactory(new PropertyValueFactory<>("stage"));

        testerTableView.setItems(controller.getBugTesterModel());

        testerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        testerTableView.setRowFactory(x -> changeSelection());
        testerTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectedItem(newValue)));

        stackPaneT = new StackPane();

        Text sceneTitle = new Text("Lista bug-uri");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);
        asignBtn = new Button("Asigneaza");
        asignBtn.setDisable(true);
        asignBtn.setOnAction(x -> asignBug());
        viewSolvedBtn = new Button("Vizualizeaza bug-uri reparate");
        viewSolvedBtn.setOnAction(x -> viewSolvedBugs());

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPrefHeight(70);
        BorderPane bp = new BorderPane();
        gp.add(sceneTitle, 2, 2, 1, 1);
        gp.add(asignBtn, 4, 2, 1, 1);
        gp.add(viewSolvedBtn, 5, 2, 1, 1);

        bp.setTop(gp);
        testerTableView.setPrefWidth(420);
        bp.setCenter(testerTableView);
        stackPaneT.getChildren().add(bp);
    }

    private Stage getSolvedBugsStage() {
        Stage s = new Stage();
        BorderPane sbPane = new BorderPane();
        StackPane stackPaneSB = new StackPane();
        tableViewSB = new TableView<>();

        TableColumn<Bug, String> namingCol = new TableColumn<>("Denumire bug");
        TableColumn<Bug, String> assignedToCol = new TableColumn<>("Asignat pentru");
        TableColumn<Bug, String> solvedByCol = new TableColumn<>("Reparat de");
        tableViewSB.getColumns().addAll(namingCol, assignedToCol, solvedByCol);


        namingCol.setCellValueFactory(new PropertyValueFactory<>("naming"));
        assignedToCol.setCellValueFactory(new PropertyValueFactory<>("responsibleProgrammer"));
        solvedByCol.setCellValueFactory(new PropertyValueFactory<>("solvedBy"));

        tableViewSB.setItems(controller.getSBModel());
        tableViewSB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewSB.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectedSolvedBug(newValue)));


        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPrefHeight(100);
        BorderPane bp = new BorderPane();
        testBtn = new Button("Testeaza");
        testBtn.setDisable(true);
        testBtn.setOnAction(x -> testCode());
        gp.add(testBtn, 0, 1, 1, 1);//testButton
        bp.setBottom(gp);
        bp.setCenter(tableViewSB);
        stackPaneSB.getChildren().add(bp);


        sbPane.setCenter(stackPaneSB);
        Scene sc = new Scene(sbPane, 400, 400);
        s.setScene(sc);
        return s;
    }

    private void selectedSolvedBug(Bug newValue) {
        if (newValue != null) {
            testBtn.setDisable(false);
            return;
        }
        testBtn.setDisable(true);
    }

    private void testCode() {
        Bug bug = tableViewSB.getSelectionModel().getSelectedItem();
        boolean solved = controller.testCodeSolvedBug(bug);
        if (solved) {
            showMessage("Testare rezolvare bug", "confirm", "Bug-ul a fost eliminat\nStaduiul bug-ului a devenit Inchis");
            return;
        }
        showMessage("Testare rezolvare bug", "confirm", "Bug-ul inca persista in sistem\nStadiul bug-ului a devenit Redeschis");
    }

    private void viewSolvedBugs() {
        Stage solvedBugsStage = getSolvedBugsStage();
        solvedBugsStage.setOnCloseRequest(x -> viewSolvedBtn.setDisable(false));
        solvedBugsStage.setOnShowing(x -> viewSolvedBtn.setDisable(true));
        solvedBugsStage.show();
    }

    private void asignBug() {
        List<String> programmers = controller.getProgrammersList();

        ChoiceDialog<String> dialog = new ChoiceDialog<>(programmers.get(0), programmers);
        dialog.setTitle("Asignare bug");
        dialog.setContentText("Alegeti programatorul:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(prog -> {
            controller.asignBug(prog, testerTableView.getSelectionModel().getSelectedItem());
            showMessage("Asignare bug", "Confirm", "Bug-ul a fost asignat cu succes");
        });
    }


    private TableRow<Bug> changeSelection() {
        TableRow<Bug> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty()) {
                testerTableView.getSelectionModel().clearSelection();
            }
        });
        return row;
    }

    private void selectedItem(Bug bug) {
        if (bug == null) {
            clearFields();
            asignBtn.setDisable(true);
            return;
        }
        naming_field.setText(bug.getNaming());
        description_area.setText(bug.getDescription());
        asignBtn.setDisable(true);
        if (bug.getStage() != bugs.model.Stage.Inchis && bug.getResponsibleProgrammer() == null) {
            asignBtn.setDisable(false);
        }
    }


    private void clearFields() {
        naming_field.setText("");
        description_area.setText("");
    }
}
