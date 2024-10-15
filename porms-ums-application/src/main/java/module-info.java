module porms.ums.application {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;
    requires org.slf4j;
    requires org.apache.logging.log4j.slf4j2.impl;

    opens com.system.demo to javafx.fxml;
    opens com.system.demo.controllers to javafx.fxml;
    opens com.system.demo.controllers.main to javafx.fxml;
    opens com.system.demo.controllers.modal to javafx.fxml;
    opens com.system.demo.appl.model.user to javafx.fxml;

    exports com.system.demo.controllers;
    exports com.system.demo;
    exports com.system.demo.controllers.main;
    exports com.system.demo.controllers.modal;
    exports com.system.demo.appl.model.user;
}
