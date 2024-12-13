package com.rc.porms.controllers.dashboard;

import com.rc.porms.appl.facade.user.UserFacade;
import com.rc.porms.appl.facade.user.impl.UserFacadeImpl;
import com.rc.porms.appl.model.user.User;
import com.rc.porms.data.user.dao.UserDao;
import com.rc.porms.data.user.dao.impl.UserDaoImpl;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private Button burgerButton;

    @FXML
    private ImageView burgerIcon;

    @FXML
    private AnchorPane sidebarPane;

    private boolean sidebarVisible = false;

    @FXML
    private TableView<User> table;

    private UserFacade userFacade;

    public TableViewController() {
        UserDao userDao = new UserDaoImpl();
        userFacade = new UserFacadeImpl(userDao);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
        refreshTable();
    }

    private void setupTable() {
        TableColumn<User, String> usernameColumn = new TableColumn<>("USERNAME");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        usernameColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<User, Timestamp> lastLoginColumn = new TableColumn<>("LAST LOGIN");
        lastLoginColumn.setCellValueFactory(new PropertyValueFactory<>("lastLoginDate"));
        lastLoginColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        lastLoginColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<User, Timestamp> joinDateColumn = new TableColumn<>("JOIN DATE");
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        joinDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        joinDateColumn.setCellFactory(getDateCellFactory());
        joinDateColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<User, String> roleColumn = new TableColumn<>("ROLE");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        roleColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<User, Integer> isLockedColumn = new TableColumn<>("IS LOCKED");
        isLockedColumn.setCellValueFactory(new PropertyValueFactory<>("isLocked"));
        isLockedColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        isLockedColumn.setStyle("-fx-alignment: CENTER;");
        isLockedColumn.setCellFactory(column -> new TableCell<>() {
            private final Button lockButton = new Button();

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                Image image = item == 1
                        ? new Image(getClass().getResourceAsStream("/assets/lock.png"))
                        : new Image(getClass().getResourceAsStream("/assets/unlock.png"));

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);
                lockButton.setGraphic(imageView);

                lockButton.setOnAction(event -> {
                    User currentUser = getTableView().getItems().get(getIndex());
                    String username = currentUser.getUsername();

                    if (item == 1) {
                        userFacade.setUnLocked(username);
                    } else {
                        userFacade.setLocked(username);
                    }

                    refreshTable();
                });

                setGraphic(lockButton);
            }
        });

        table.getColumns().addAll(usernameColumn, lastLoginColumn, joinDateColumn, roleColumn, isLockedColumn);
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

    private void refreshTable() {
        List<User> users = userFacade.getAllUsers();
        ObservableList<User> data = FXCollections.observableArrayList(users);
        table.setItems(data);
    }

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
}
