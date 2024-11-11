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

    UserDao userFacade = new UserDaoImpl();

    @FXML
    protected void logButtonOnAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getCharacters().toString();

        try {
            User currentUser = userFacade.getUserByUsername(username);
            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Error", "Username and password are required.", Alert.AlertType.ERROR);
            } else if (currentUser != null && password.equals(currentUser.getPassword())) {
                String role = userFacade.getUserRoleByUsername(username); // Fetch user role from database
                openDashboardWindow(event, role);
            } else {
                showAlert("Login Failed", "Please double-check your username and password.", Alert.AlertType.ERROR);
            }
        } catch (Exception ex) {
            showAlert("Error", "An error occurred during login: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openDashboardWindow(ActionEvent event, String role) {
        try {
            FXMLLoader loader;
            if ("admin".equals(role)) {
                loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/views/OffenseList.fxml"));
            }
            Parent root = loader.load();


            // Get the stage and set the scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
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

    public void signButtonOnAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/CreateAcc.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
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