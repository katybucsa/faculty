package bugs.client;

import bugs.model.Bug;
import bugs.model.User;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import static javafx.scene.paint.Color.PURPLE;

public class ProgramatorView extends Stage {
    private Controller controller;
    private User user;
    private Scene scene;
    private BorderPane mainPane;
    private StackPane stackPaneA;
    private Button repairBtn;
    private TableView<Bug> progTableView = new TableView<>();


    public ProgramatorView(Controller controller, User user) {
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
        createProgTable();
        mainPane.setCenter(stackPaneA);
        scene = new Scene(mainPane, 400, 400);
        this.setScene(scene);
    }


    public void createProgTable() {
        TableColumn<Bug, String> namingCol = new TableColumn<>("Denumire");
        TableColumn<Bug, String> descriptionCol = new TableColumn<>("Descriere");
        TableColumn<Bug, Stage> stageCol = new TableColumn<>("Stadiu");
        TableColumn<Bug, String> progCol = new TableColumn<>("Programator");
        progCol.setVisible(false);
        progTableView.getColumns().addAll(namingCol, descriptionCol, stageCol, progCol);

        namingCol.setCellValueFactory(new PropertyValueFactory<>("naming"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        stageCol.setCellValueFactory(new PropertyValueFactory<>("stage"));
        progCol.setCellValueFactory(new PropertyValueFactory<>("responsibleProgrammer"));

        progTableView.setItems(controller.getBugProgModel());

        progTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        progTableView.setRowFactory(x -> changeSelection());
        progTableView.setRowFactory(x -> new TableRow<Bug>() {
            @Override
            protected void updateItem(Bug row, boolean empty) {
                super.updateItem(row, empty);
                if (empty) {
                    setStyle("");
                }
                if (getIndex() <= progTableView.getItems().size() && row != null && row.getResponsibleProgrammer() != null
                        && row.getResponsibleProgrammer().getUsername().equals(user.getUsername())) {
                    setStyle("-fx-background-color: red");
                } else {
                    setStyle("");
                }
            }
        });
        progTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectedItem(newValue)));
        stackPaneA = new StackPane();

        Text sceneTitle = new Text("Lista bug-uri");
        sceneTitle.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        sceneTitle.setFill(PURPLE);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPrefHeight(70);
        gp.add(sceneTitle, 3, 2, 1, 1);
        gp.setOnMouseClicked(x -> progTableView.getSelectionModel().clearSelection());

        GridPane gpb = new GridPane();
        gpb.setAlignment(Pos.CENTER);
        gpb.setHgap(10);
        gpb.setVgap(10);
        gpb.setPrefHeight(70);
        repairBtn = new Button("Repară bug");
        repairBtn.setOnAction(x -> repairBug());
        repairBtn.setDisable(true);
        gpb.add(repairBtn, 3, 2, 1, 1);
        gpb.setOnMouseClicked(x -> progTableView.getSelectionModel().clearSelection());

        BorderPane bp = new BorderPane();
        bp.setTop(gp);
        bp.setBottom(gpb);
        progTableView.setPrefWidth(420);
        bp.setCenter(progTableView);
        stackPaneA.getChildren().add(bp);
    }


    private void repairBug() {
        Bug bug = progTableView.getSelectionModel().getSelectedItem();
        bug.setSolvedBy(this.user);
        controller.repairBug(bug);
    }


    private TableRow<Bug> changeSelection() {
        TableRow<Bug> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && row.isEmpty()) {
                progTableView.getSelectionModel().clearSelection();
            }
        });
        return row;
    }


    private void selectedItem(Bug bug) {
        if (bug == null) {
            progTableView.getSelectionModel().clearSelection();
            repairBtn.setDisable(true);
            return;
        }
        repairBtn.setDisable(false);
    }
}
