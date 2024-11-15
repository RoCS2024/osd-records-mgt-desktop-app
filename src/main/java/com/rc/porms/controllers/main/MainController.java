package com.rc.porms.controllers.main;

import com.rc.porms.appl.model.user.User;
import com.rc.porms.data.user.dao.UserDao;
import com.rc.porms.data.user.dao.impl.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class MainController {

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
                System.out.println(password);
                System.out.println(currentUser.getPassword());
            } else if(currentUser != null && BCrypt.checkpw(password, currentUser.getPassword())) {
                showAlert("Login Successful", "Welcome " + username + "!", Alert.AlertType.INFORMATION);
            }
            else{
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
}
