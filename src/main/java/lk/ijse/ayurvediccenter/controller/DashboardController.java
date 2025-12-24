package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label lblDayField;

    @FXML
    private Label lblDate;

    @FXML
    public BorderPane ancUserView;

    @FXML
    public AnchorPane mainContent;

    @FXML
    private ComboBox<String> cmbPages;

    private final Map<String, String> pagesMap = Map.of(
            "Profile", "/lk/ijse/ayurvediccenter/view/Profile.fxml",
            "Setting", "/lk/ijse/ayurvediccenter/view/Setting.fxml",
            "Change Password", "/lk/ijse/ayurvediccenter/view/ChangePassword.fxml",
            "Emp Management", "/lk/ijse/ayurvediccenter/view/EmpManagement.fxml"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(LocalDate.now().toString());
        lblDayField.setText(LocalDate.now().getDayOfWeek().toString());

        cmbPages.getItems().addAll(pagesMap.keySet());

        // Handle selection changes
        cmbPages.setOnAction(event -> handlePageSelect());


    }


    @FXML
    private void handlePageSelect() {
        String selected = cmbPages.getValue();
        if (selected == null) return;

            String path = pagesMap.get(selected);
            if (path != null) {
                navigateTo(path);

        }
    }

    @FXML
    public void navigateTo(String path) {
        try {
            mainContent.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(mainContent.widthProperty());
            anchorPane.prefHeightProperty().bind(mainContent.heightProperty());
            mainContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

}
