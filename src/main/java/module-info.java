module lk.ijse.ayurvediccenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;

    opens lk.ijse.ayurvediccenter to javafx.fxml;
    opens lk.ijse.ayurvediccenter.controller to javafx.fxml;
    opens lk.ijse.ayurvediccenter.dto to javafx.base;

    exports lk.ijse.ayurvediccenter.controller;
    exports lk.ijse.ayurvediccenter;
}
