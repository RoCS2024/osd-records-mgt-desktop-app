package com.rc.porms.controllers.dashboard;

import com.rc.porms.appl.model.user.User;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    //for sidebar uses
    @FXML
    private Button burgerButton;

    @FXML
    private ImageView burgerIcon;

    @FXML
    private AnchorPane sidebarPane;

    private boolean sidebarVisible = false;

    //for table id
    @FXML
    TableView table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getItems().clear();
        //List<User> users = userFacade.getAllUsers();
       // ObservableList<User> data = FXCollections.observableArrayList(users);
       // table.setItems(data);

        TableColumn<User, String> usernameColumn = new TableColumn<>("USERNAME");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        usernameColumn.getStyleClass().addAll("username-column");

        TableColumn<User, Integer> lastLoginColumn = new TableColumn<>("LAST LOGIN");
        lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));
        lastLoginColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        lastLoginColumn.getStyleClass().addAll("last-login-column");

        TableColumn<User, Timestamp> joinDateColumn = new TableColumn<>("JOIN DATE");
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        joinDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        joinDateColumn.getStyleClass().addAll("join-date-column");
        joinDateColumn.setCellFactory(getDateCellFactory());

        TableColumn<User, Timestamp> roleColumn = new TableColumn<>("ROLE");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        roleColumn.getStyleClass().addAll("role-column");

        TableColumn<User, String> authoritiesColumn = new TableColumn<>("AUTHORITIES");
        authoritiesColumn.setCellValueFactory(new PropertyValueFactory<>("authorities"));
        authoritiesColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        authoritiesColumn.getStyleClass().addAll("authorities-column");

        TableColumn<User, String> otpColumn = new TableColumn<>("OTP");
        otpColumn.setCellValueFactory(new PropertyValueFactory<>("otp"));
        otpColumn.getStyleClass().addAll("otp-column");
        otpColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width

        TableColumn<User, Integer> isActiveColumn = new TableColumn<>("IS ACTIVE");
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        isActiveColumn.getStyleClass().addAll("is-active-column");
        isActiveColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width

        TableColumn<User, Integer> isLockedColumn = new TableColumn<>("IS LOCKED");
        isLockedColumn.setCellValueFactory(new PropertyValueFactory<>("isLocked"));
        isLockedColumn.getStyleClass().addAll("is-locked-column");
        isLockedColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width


        TableColumn<User, String> actionColumn = new TableColumn<>("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        actionColumn.getStyleClass().addAll("action-column");
        actionColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4)); // 40% width
        actionColumn.setCellFactory(cell -> {
            final Button editButton = new Button();
            TableCell<User, String> cellInstance = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/pencil.png"))));
                        editButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            //showEditUser(user, (ActionEvent) event);
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

        table.getColumns().addAll(usernameColumn, lastLoginColumn,
                joinDateColumn, roleColumn, authoritiesColumn,otpColumn, isActiveColumn,
                isLockedColumn, actionColumn);
    }

    private Callback<TableColumn<User, Timestamp>, TableCell<User, Timestamp>> getDateCellFactory() {
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

    //button actions
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
}

