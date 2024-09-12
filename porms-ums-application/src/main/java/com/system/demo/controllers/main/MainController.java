package com.system.demo.controllers.main;

import com.system.demo.appl.model.user.User;
import com.system.demo.data.user.dao.UserDao;
import com.system.demo.data.user.dao.impl.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordShown;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleButton toggleButton;

    @FXML
    private Button logButton;

    private User user;

    UserDao userFacade = new UserDaoImpl();

    @FXML
    protected void logButtonOnAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getCharacters().toString();
        String password2 = passwordShown.getText();

        try {
            User currentUser = userFacade.getUserByUsername(username);

            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username and password are required.");
                alert.showAndWait();
            } else if(currentUser != null && BCrypt.checkpw(password, currentUser.getPassword())) {
                if(checkRoleAdmin(currentUser).equals("admin")){
                    showAlert("Login Successful", "Welcome " + username + "!", Alert.AlertType.INFORMATION);
                    openAdminDashboardWindow(event);
                } else if((checkRoleAdmin(currentUser).equals("prefect"))){
                    showAlert("Login Successful", "Welcome " + username + "!", Alert.AlertType.INFORMATION);
                    openPrefectDashboardWindow(event);
                } else {
                    showAlert("Login Failed", "Your role does not have access to this system!", Alert.AlertType.ERROR);
                }
            }
            else{
                showAlert("Login Failed", "Please double-check your username and password.", Alert.AlertType.ERROR);
            }
        } catch (Exception ex) {
            showAlert("Error", "An error occurred during login: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }
    public String checkRoleAdmin(User currentUser){
        try{
            if(currentUser.getRole().equals("ROLE_ADMIN")){
                return "admin";
            } else if(currentUser.getRole().equals("ROLE_PREFECT")) {
                return "prefect";
            }
        } catch (Exception e) {
            showAlert("Error", "An error occurred during validating role: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        return "user";
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openAdminDashboardWindow(ActionEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);

            dashboardStage.initStyle(StageStyle.UNDECORATED);

            dashboardStage.show();
        } catch (IOException e) {
            System.err.println("Error opening dashboard window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void openPrefectDashboardWindow(ActionEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/OffenseList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);

            dashboardStage.initStyle(StageStyle.UNDECORATED);

            dashboardStage.show();
        } catch (IOException e) {
            System.err.println("Error opening dashboard window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void changeVisibility(ActionEvent event){
        if(toggleButton.isSelected()){
            passwordShown.setText(passwordField.getText());
            passwordShown.setVisible(true);
            passwordField.setVisible(false);
            toggleButton.setVisible(false);
            return;
        }
        passwordField.setText(passwordShown.getText());
        passwordField.setVisible(true);
        passwordShown.setVisible(false);
        toggleButton.setVisible(true);
    }

    @FXML
    void changeVisibility2(ActionEvent event){
        if(toggleButton.isSelected()){
            passwordField.setText(passwordShown.getText());
            passwordField.setVisible(true);
            passwordShown.setVisible(false);
            toggleButton.setVisible(true);
            return;
        }
        passwordShown.setText(passwordField.getText());
        passwordShown.setVisible(true);
        passwordField.setVisible(false);
        toggleButton.setVisible(false);
    }


    @FXML
    protected void quitApp(MouseEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleForgotPsw(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ForgotPsw.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
