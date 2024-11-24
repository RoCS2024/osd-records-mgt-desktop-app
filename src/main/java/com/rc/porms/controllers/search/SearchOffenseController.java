package com.rc.porms.controllers.search;

import com.rc.porms.appl.model.offense.Offense;
import com.rc.porms.controllers.modal.EditOffenseController;
import com.rc.porms.data.prefect.offense.OffenseDao;
import com.rc.porms.data.prefect.offense.impl.OffenseDaoImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchOffenseController implements Initializable {
    //for table id
    @FXML
    TableView table;

    private String description;

    @FXML
    private Button previousButton;

    OffenseDao offenceFacade =  new OffenseDaoImpl();

    public void initData(String description) {this.description = description;}

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){
            previousButton.setOnAction(event -> {handleBack2Previous((ActionEvent) event);});

            if (description == null || description.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Offense Name Missing", "Please provide an offense name.");
                return;
            }
            System.out.println("Offensename: " + this.description);
            table.getItems().clear();
            Offense offenseData = offenceFacade.getOffenseByName(description);
            System.out.println(description);

            if (offenseData == null) {
                // User not found, show alert
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("The user with username '" + description + "' was not found.");
                    alert.showAndWait();
                });
            } else{

                LoadTables(offenseData);
            }
            }

    public void LoadTables(Offense offenseData){

        ObservableList<Offense> data = FXCollections.observableArrayList(offenseData);
        table.setItems(data);

        TableColumn offenseTypeColumn = new TableColumn("OFFENSE TYPE");
        offenseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        offenseTypeColumn.getStyleClass().addAll("type-column");

        TableColumn offenseDescriptionColumn = new TableColumn("OFFENSE DESCRIPTION");
        offenseDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        offenseDescriptionColumn.getStyleClass().addAll("description-column");

        TableColumn<Offense, String> actionColumn = new TableColumn<>("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        actionColumn.getStyleClass().addAll("action-column");
        actionColumn.setCellFactory(cell -> {
            final Button editButton = new Button();
            TableCell<Offense, String> cellInstance = new TableCell<>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/pencil.png"))));
                        editButton.setOnAction(event -> {
                            Offense offense = getTableView().getItems().get(getIndex());
                            showEditOffense(offense, (ActionEvent) event);
                        });
                        HBox hbox = new HBox(editButton);
                        hbox.setSpacing(10);
                        hbox.setAlignment(Pos.BASELINE_CENTER);
                        setGraphic(hbox);
                        setText(null);
                    }
                }
            };
            return cellInstance;
        });

        table.getColumns().setAll(offenseTypeColumn, offenseDescriptionColumn, actionColumn);

    }

    //show details in edit button
    private void showEditOffense(Offense offense, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            Stage editStage = new Stage();
            editStage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/EditOffense.fxml"));
            AnchorPane editLayout = new AnchorPane();
            editLayout = loader.load();
            EditOffenseController editOffenseController = loader.getController();
            editOffenseController.setOffense(offense);
            Scene scene = new Scene(editLayout);
            editStage.setScene(scene);
            editStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBack2Previous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OffenseList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}