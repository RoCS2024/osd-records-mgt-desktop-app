package com.rc.porms.controllers.dashboard;

import com.rc.porms.PrefectInfoMgtApplication;
import com.rc.porms.StudentInfoMgtApplication;
import com.rc.porms.appl.facade.prefect.communityservice.CommunityServiceFacade;
import com.rc.porms.appl.facade.student.StudentFacade;
import com.rc.porms.appl.model.communityservice.CommunityService;
import com.rc.porms.appl.model.student.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CsHistoryController implements Initializable{
    //for sidebar uses
    @FXML
    private Button burgerButton;

    @FXML
    private ImageView burgerIcon;

    @FXML
    private AnchorPane sidebarPane;

    private boolean sidebarVisible = false;

    //for search
    @FXML
    private TextField searchField;

    //for table id
    @FXML
    TableView table;

    private CommunityServiceFacade communityServiceFacade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PrefectInfoMgtApplication app = new PrefectInfoMgtApplication();

        communityServiceFacade = app.getCommunityserviceFacade();

        table.getItems().clear();
        List<CommunityService> communityServices = communityServiceFacade.getAllCs();
        ObservableList<CommunityService> data = FXCollections.observableArrayList(communityServices);
        table.setItems(data);

        TableColumn<CommunityService, String> studColumn = new TableColumn<>("NAME");
        studColumn.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getStudent().getFirstName();
            String lastName = cellData.getValue().getStudent().getLastName();
            return new SimpleStringProperty(firstName + " " + lastName);
        });
        studColumn.getStyleClass().addAll("student-column");

        TableColumn<CommunityService, String> stationColumn = new TableColumn<>("STATION");
        stationColumn.setCellValueFactory(cellData -> {
            String station_name = cellData.getValue().getStation_name();
            return new SimpleStringProperty(station_name);
        });
        stationColumn.getStyleClass().addAll("station-column");

        TableColumn<CommunityService, String> dateRendered = new TableColumn<>("DATE");
        dateRendered.setCellValueFactory(cellData -> {
            Timestamp date = cellData.getValue().getDate_rendered();
            return new SimpleStringProperty(date.toString());
        });
        dateRendered.getStyleClass().addAll("date-column");

        TableColumn<CommunityService, String> reasonOfCS = new TableColumn<>("REASON");
        reasonOfCS.setCellValueFactory(cellData -> {
            String reason = cellData.getValue().getReason_of_cs();
            return new SimpleStringProperty(reason);
        });
        reasonOfCS.getStyleClass().addAll("reason-column");

        TableColumn<CommunityService, String> hoursCompleted = new TableColumn<>("HOURS COMPLETED");
        hoursCompleted.setCellValueFactory(cellData -> {
            int completed = cellData.getValue().getHours_completed();

            return new SimpleStringProperty(String.valueOf(completed));
        });
        hoursCompleted.getStyleClass().addAll("completed-column");

        TableColumn<CommunityService, String> natureOfWork = new TableColumn<>("NATURE OF WORK");
        natureOfWork.setCellValueFactory(cellData -> {
            String work = cellData.getValue().getNature_of_work();

            return new SimpleStringProperty(String.valueOf(work));
        });
        natureOfWork.getStyleClass().addAll("nature-column");


        table.getColumns().addAll(studColumn, stationColumn, dateRendered, reasonOfCS, hoursCompleted, natureOfWork);
    }

    private Callback<TableColumn<CommunityService, Timestamp>, TableCell<CommunityService, Timestamp>> getDateCellFactory() {
        return column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDate date = item.toLocalDateTime().toLocalDate();
                    setText(formatter.format(date));
                }
            }
        };
    }

    @FXML
    protected void handleIconUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconOffense(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/OffenseList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconViolationList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViolationList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconCommunityService(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CommunityService.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconLogout (MouseEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

            Stage dashboardStage = new Stage();
            dashboardStage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for sidebar actions
    @FXML
    private void toggleSidebarVisibility(ActionEvent event) {
        sidebarVisible = !sidebarVisible;
        sidebarPane.setVisible(sidebarVisible);

        if (sidebarVisible) {
            BorderPane.setMargin(sidebarPane, new Insets(0));
        } else {
            BorderPane.setMargin(sidebarPane, new Insets(0, -125.0, 0, 0));
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
