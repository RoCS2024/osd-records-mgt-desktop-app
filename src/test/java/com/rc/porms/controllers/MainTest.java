package com.rc.porms.controllers;

import com.rc.porms.controllers.main.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.TestFx;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.util.NodeQueryUtils.isVisible;


class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/views/MainView.fxml"));

        BorderPane mainLayout = new BorderPane();
        mainLayout = loader.load();

        Scene scene = new Scene(mainLayout);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    @TestFx
    public void testLoginValidation() {
        MainController.setTestMode(true);


        //testing kung ang makaka log in if walang ilagay sa username and password
        clickOn("#logButton");
        sleep(2000);


        //testing kung ang makaka log in if walang ilagay sa username
        clickOn("#passwordField");
        write("Password@123");
        clickOn("#logButton");
        sleep(2000);
        clickOn("#passwordField");
        eraseText(12);


        //testing kung ang makaka log in if walang ilagay sa password
        clickOn("#usernameField");
        write("conrad");
        clickOn("#logButton");
        sleep(2000);
        clickOn("#usernameField");
        eraseText(6);


        //testing kung makaka login if mali yung username tas tama yung password
        clickOn("#usernameField");
        write("wrongusername");
        clickOn("#passwordField");
        write("Password@123");
        clickOn("#logButton");
        sleep(2000);
        clickOn("#usernameField");
        eraseText(13);
        clickOn("#passwordField");
        eraseText(12);

        //testing kung makaka log in kahit mali yung password
        clickOn("#usernameField");
        write("conrad");
        clickOn("#passwordField");
        write("Wrongpass@123");
        clickOn("#eyeBrow");
        sleep(1000);
        clickOn("#eyeBrow");
        clickOn("#logButton");
        sleep(2000);
        clickOn("#usernameField");
        eraseText(6);
        clickOn("#passwordField");
        eraseText(13);

        //test kung makaka log in if tama yung credentials
        clickOn("#usernameField");
        write("conrad");
        clickOn("#passwordField");
        write("Password@123");
        clickOn("#logButton");
        sleep(2000);
    }

    @TestFx
    public void testLoginAndAddOffense() {
        MainController.setTestMode(true);

        clickOn("#usernameField");
        write("conrad");
        clickOn("#passwordField");
        write("Password@123");
        clickOn("#logButton");

        verifyThat("#table", isVisible());
        sleep(500);

        //test if makakapaglagay ng Minor and major offense kung walang description
        clickOn("#addOffenseButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());

        clickOn("#offenseField");
        write("");
        clickOn("#comboBox");
        sleep(1000);
        clickOn("Minor");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(1000);

        verifyThat(".dialog-pane", isVisible());
        clickOn("OK");

        clickOn("#comboBox");
        sleep(1000);
        clickOn("Major");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(1000);

        verifyThat(".dialog-pane", isVisible());
        clickOn("OK");

        clickOn("#cancelAddButton");
        sleep(2000);

        //test if makakapag input ng offense if walang major or minor
        clickOn("#addOffenseButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());

        clickOn("#offenseField");
        write("Minor and Major");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());
        clickOn("OK");
        sleep(1000);
        clickOn("#cancelAddButton");
        sleep(2000);

        //Test if pwede mag lagay ng alpha numeric sa offense
        clickOn("#addOffenseButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());

        clickOn("#offenseField");
        write("Smoking & Drinking");
        clickOn("#comboBox");
        sleep(1000);
        clickOn("Major");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(1000);


        //test para makapag add ng offense minor
        clickOn("#addOffenseButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());

        clickOn("#offenseField");
        write("Minor Test");
        clickOn("#comboBox");
        sleep(1000);
        clickOn("Minor");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(1000);

        //test para makapag add ng offense Major
        clickOn("#addOffenseButton");
        sleep(1000);
        verifyThat(".dialog-pane", isVisible());

        clickOn("#offenseField");
        write("Major Test");
        clickOn("#comboBox");
        sleep(1000);
        clickOn("Major");
        sleep(1000);
        clickOn("#saveAddButton");
        sleep(2000);
        verifyThat("#table", isVisible());
    }
}