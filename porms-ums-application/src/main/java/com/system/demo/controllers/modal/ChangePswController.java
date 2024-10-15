package com.system.demo.controllers.modal;

import com.system.demo.appl.model.user.User;
import com.system.demo.data.connection.ConnectionHelper;
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

import java.sql.*;

public class ChangePswController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField currentPswField;

    @FXML
    private TextField showCurrentPsw;

    @FXML
    private PasswordField newPswField;

    @FXML
    private TextField showNewPsw;

    @FXML
    private PasswordField confirmPswField;

    @FXML
    private TextField showConfirmPsw;

    @FXML
    private Button saveChangePswButton;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private ToggleButton toggleButton2;

    @FXML
    private ToggleButton toggleButton3;

    @FXML
    private ToggleButton toggleButton4;

    @FXML
    private ToggleButton toggleButton5;

    @FXML
    private ToggleButton toggleButton6;

    private User user;

    UserDao userFacade = new UserDaoImpl();

    @FXML
    protected void setSaveChangePswClicked(ActionEvent event) {
        String currentPassword = currentPswField.getText();
        String newPassword = newPswField.getText();
        String confirmPassword = confirmPswField.getText();
        String username = usernameField.getText();

        if (!newPassword.equals(confirmPassword)) {
            showAlert("Error", "Password does not match. Please try again", Alert.AlertType.ERROR);
            return;
        }

        if (!newPassword.matches("[a-zA-Z0-9~`!@#$%^&*()_={}\\|:;\"'<,>.?/]+")) {
            showAlert("Error", "New password should contain only alphanumeric characters and symbols.", Alert.AlertType.ERROR);
            return;
        }

        if (currentPassword.equals(newPassword)) {
            showAlert("Error", "New password should be different from the current password.", Alert.AlertType.ERROR);
            return;
        }

        if (!validateCurrentPassword(username, currentPassword)) {
            showAlert("Error", "Current password is incorrect.", Alert.AlertType.ERROR);
            return;
        }

        User updatePsw = new User();
        updatePsw.setUsername(username);
        updatePsw.setPassword(newPswField.getCharacters().toString());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        updatePsw.setDateModified(timestamp);

        try {
            userFacade.updatePassword(updatePsw);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateCurrentPassword(String username, String currentPassword) {
        String query = "SELECT PASSWORD FROM LOGIN WHERE USERNAME = ?";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    return currentPassword.equals(storedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //show password button action
    @FXML
    void changeVisibility(ActionEvent event){
        if(toggleButton.isSelected()){
            showCurrentPsw.setText(currentPswField.getText());
            showCurrentPsw.setVisible(true);
            currentPswField.setVisible(false);
            toggleButton.setVisible(false);
            return;
        }
        currentPswField.setText(showCurrentPsw.getText());
        currentPswField.setVisible(true);
        showCurrentPsw.setVisible(false);
        toggleButton.setVisible(true);
    }

    @FXML
    void changeVisibility2(ActionEvent event){
        if(toggleButton2.isSelected()){
            currentPswField.setText(showCurrentPsw.getText());
            currentPswField.setVisible(true);
            showCurrentPsw.setVisible(false);
            toggleButton.setVisible(true);
            return;
        }
        showCurrentPsw.setText(currentPswField.getText());
        showCurrentPsw.setVisible(true);
        currentPswField.setVisible(false);
        toggleButton.setVisible(false);
    }

    @FXML
    void changeVisibility3(ActionEvent event){
        if(toggleButton3.isSelected()){
            showNewPsw.setText(newPswField.getText());
            showNewPsw.setVisible(true);
            newPswField.setVisible(false);
            toggleButton3.setVisible(false);
            return;
        }
        newPswField.setText(showNewPsw.getText());
        newPswField.setVisible(true);
        showNewPsw.setVisible(false);
        toggleButton3.setVisible(true);
    }

    @FXML
    void changeVisibility4(ActionEvent event){
        if(toggleButton4.isSelected()){
            newPswField.setText(showNewPsw.getText());
            newPswField.setVisible(true);
            showNewPsw.setVisible(false);
            toggleButton3.setVisible(true);
            return;
        }
        showNewPsw.setText(newPswField.getText());
        showNewPsw.setVisible(true);
        newPswField.setVisible(false);
        toggleButton3.setVisible(false);
    }

    @FXML
    void changeVisibility5(ActionEvent event){
        if(toggleButton5.isSelected()){
            showConfirmPsw.setText(confirmPswField.getText());
            showConfirmPsw.setVisible(true);
            confirmPswField.setVisible(false);
            toggleButton5.setVisible(false);
            return;
        }
        confirmPswField.setText(showConfirmPsw.getText());
        confirmPswField.setVisible(true);
        showConfirmPsw.setVisible(false);
        toggleButton5.setVisible(true);
    }

    @FXML
    void changeVisibility6(ActionEvent event){
        if(toggleButton6.isSelected()){
            confirmPswField.setText(showConfirmPsw.getText());
            confirmPswField.setVisible(true);
            showConfirmPsw.setVisible(false);
            toggleButton5.setVisible(true);
            return;
        }
        showConfirmPsw.setText(confirmPswField.getText());
        showConfirmPsw.setVisible(true);
        confirmPswField.setVisible(false);
        toggleButton5.setVisible(false);
    }

    //display user info in ui
    public void setUser(User user) {
        this.user = user;
        usernameField.setText(user.getUsername());
    }

    @FXML
    protected void handleCancelChangePsw(MouseEvent event) {
        try {
            Stage previousStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage2.close();

            Stage dashboardStage2 = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/views/UserList.fxml"));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2);
            dashboardStage2.setScene(scene2);
            dashboardStage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
