package com.rc.porms.controllers.dashboard;

import com.rc.porms.PrefectInfoMgtApplication;
import com.rc.porms.StudentInfoMgtApplication;
import com.rc.porms.appl.facade.employee.EmployeeFacade;
import com.rc.porms.appl.facade.prefect.violation.ViolationFacade;
import com.rc.porms.appl.facade.student.StudentFacade;
import com.rc.porms.appl.model.student.Student;
import com.rc.porms.appl.model.violation.Violation;
import com.rc.porms.controllers.modal.EditViolationController;
import com.rc.porms.controllers.search.SearchViolationController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViolationController implements Initializable {
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

    @FXML
    private ToggleButton searchButton;

    //for table id
    @FXML
    TableView tableView;

    private ViolationFacade violationFacade;

    private StudentFacade studentFacade;

    private EmployeeFacade employeeFacade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PrefectInfoMgtApplication app = new PrefectInfoMgtApplication();
        violationFacade = app.getViolationFacade();

        tableView.getItems().clear();
        List<Violation> violations = violationFacade.getAllViolation();
        ObservableList<Violation> data = FXCollections.observableArrayList(violations);
        tableView.setItems(data);

        TableColumn<Violation, String> studColumn = new TableColumn<>("STUDENT NAME");
        studColumn.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getStudent().getFirstName();
            String lastName = cellData.getValue().getStudent().getLastName();
            return new SimpleStringProperty(firstName + " " + lastName);
        });
        studColumn.getStyleClass().addAll("student-column");

        TableColumn<Violation, String> offenseColumn = new TableColumn<>("OFFENSE");
        offenseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOffense().getDescription()));
        offenseColumn.getStyleClass().addAll("offense-id-column");

        TableColumn<Violation, Integer> warningColumn = new TableColumn<>("WARNING NO.");
        warningColumn.setCellValueFactory(new PropertyValueFactory<>("warningNum"));
        warningColumn.getStyleClass().addAll("warning-column");

        TableColumn<Violation, Integer> csHoursColumn = new TableColumn<>("CS HOURS");
        csHoursColumn.setCellValueFactory(new PropertyValueFactory<>("commServHours"));
        csHoursColumn.getStyleClass().addAll("cs-hours-column");

        TableColumn<Violation, String> disciplinaryColumn = new TableColumn<>("DISCIPLINARY ACTION");
        disciplinaryColumn.setCellValueFactory(new PropertyValueFactory<>("disciplinaryAction"));
        disciplinaryColumn.getStyleClass().addAll("disciplinary-column");

        TableColumn<Violation, Timestamp> dateColumn = new TableColumn<>("DATE OF NOTICE");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfNotice"));
        dateColumn.getStyleClass().addAll("date-column");
        dateColumn.setCellFactory(getDateCellFactory());

        TableColumn<Violation, String> approvedByColumn = new TableColumn<>("APPROVED BY");
        approvedByColumn.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getApprovedBy().getFirstName();
            String lastName = cellData.getValue().getApprovedBy().getLastName();
            return new SimpleStringProperty(firstName + " " + lastName);
        });
        approvedByColumn.getStyleClass().addAll("approvedBy-column");

        TableColumn<Violation, String> actionColumn = new TableColumn<>("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        actionColumn.getStyleClass().addAll("action-column");
        actionColumn.setCellFactory(cell -> {
            final Button editButton_2 = new Button();
            TableCell<Violation, String> cellInstance = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        editButton_2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/pencil.png"))));
                        editButton_2.setOnAction(event -> {
                            Violation violation = getTableView().getItems().get(getIndex());
                            showEditViolation(violation, (ActionEvent) event);
                        });
                        HBox hbox = new HBox(editButton_2);
                        hbox.setSpacing(10);
                        hbox.setAlignment(Pos.BASELINE_CENTER);
                        setGraphic(hbox);
                        setText(null);
                    }
                }
            };
            return cellInstance;
        });

        tableView.getColumns().addAll(studColumn, offenseColumn , warningColumn, csHoursColumn, disciplinaryColumn, dateColumn, approvedByColumn, actionColumn);
    }

    private Callback<TableColumn<Violation, Timestamp>, TableCell<Violation, Timestamp>> getDateCellFactory() {
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

    @FXML
    protected void handleSubmitAddViolationButton(ActionEvent event) {
        Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage2.hide();
        showDashboard2();
    }

    private void showDashboard2() {
        try {
            Stage dashboardStage2 = new Stage();
            dashboardStage2.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/views/AddViolation.fxml"));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2);
            dashboardStage2.setScene(scene2);
            dashboardStage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showEditViolation(Violation violation, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            Stage editStage = new Stage();
            editStage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/EditViolation.fxml"));
            AnchorPane editLayout = new AnchorPane();
            editLayout = loader.load();
            EditViolationController editViolationController = loader.getController();
            editViolationController.setViolation(violation);
            Scene scene = new Scene(editLayout);
            editStage.setScene(scene);
            editStage.show();

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

    @FXML
    private void handleSearchButton(ActionEvent event) {
        String searchTerm = searchField.getText().trim(); // Remove leading and trailing spaces

        StudentInfoMgtApplication app = new StudentInfoMgtApplication();
        StudentFacade studentFacade = app.getStudentFacade();

        // Retrieve all students
        List<Student> allStudents = studentFacade.getAllStudents();

        // Filter students by name
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : allStudents) {
            String fullName = student.getFirstName().toLowerCase() + " " + student.getLastName().toLowerCase();

            // Check if the full name contains the search term, ignoring case
            if (fullName.contains(searchTerm.toLowerCase())) {
                matchingStudents.add(student);
            }
        }

        if (!matchingStudents.isEmpty()) {
            // Assuming you want to handle the case where there are multiple students with the same name,
            // you might display a list of matching students or navigate to a different view.
            // For simplicity, I'm assuming you're selecting the first matching student.
            Student selectedStudent = matchingStudents.get(0);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SearchViolation.fxml"));

                SearchViolationController searchController = new SearchViolationController();
                searchController.initData(selectedStudent);
                loader.setController(searchController);
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Student Not Found", "No student found with the name: " + searchTerm);
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
