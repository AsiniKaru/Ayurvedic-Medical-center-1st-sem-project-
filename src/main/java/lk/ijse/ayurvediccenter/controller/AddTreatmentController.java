package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.TreatmentModel;

import java.util.Optional;

public class AddTreatmentController {

    private TreatmentController treatmentController;

    @FXML
    private TextArea tDescriptionField;

    @FXML
    private TextArea tNameField;

    @FXML
    private TextArea tPriceField;

    @FXML
    private TextArea tTypeField;

    @FXML
    private Button saveButton;

    private boolean update ;

    int id;


    private final String TREATMENT_ID_REGEX = "^[0-9]+$";
    private final String TREATMENT_NAME_REGEX = "^[A-Za-z]+ [A-Za-z]+$";
    private final String TREATMENT_TYPE_REGEX = "^[a-zA-Z]+ [A-Za-z]+$";
    private final String TREATMENT_DESCRIPTION_REGEX = "^(?:(?:\\S+\\s+){0,299}\\S+)?\\s*$";
    private final String TREATMENT_PRICE_REGEX = "^\\d+(\\.\\d{1,2})?$";

    TreatmentModel treatmentModel = new TreatmentModel();

    public void setTreatmentController(TreatmentController treatmentController){
        this.treatmentController = treatmentController;
    }

    @FXML
    private void handleSaveTreatment(){
        String name = tNameField.getText();
        String type = tTypeField.getText();
        String description = tDescriptionField.getText();
        String price = tPriceField.getText();



        if(!name.matches(TREATMENT_NAME_REGEX)){
            new Alert(Alert.AlertType.ERROR,"Invalid Name format!", ButtonType.OK).show();
        }else if(!type.matches(TREATMENT_TYPE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid type format!", ButtonType.OK).show();
        }else if(!description.matches(TREATMENT_DESCRIPTION_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid description format!", ButtonType.OK).show();
        }else if(!price.matches(TREATMENT_PRICE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid price format!", ButtonType.OK).show();
        }else{
            try{

                ////////////////////// Save Treatment ////////////////////////////
                if(!update) {
                    TreatmentDTO teatmentDTO = new TreatmentDTO(name, type, description, Double.parseDouble(price));
                    boolean result = treatmentModel.saveTreatment(teatmentDTO);

                    if (result) {
                        treatmentController.loadTreatmentTable();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Treatment successfully added. Would you like to add another Treatment?");

                        ButtonType buttonYes = new ButtonType("Yes");
                        ButtonType buttonNo = new ButtonType("No");
                        alert.getButtonTypes().setAll(buttonYes, buttonNo);

                        Optional<ButtonType> results = alert.showAndWait();


                        if (results.isPresent() && results.get() == buttonYes) {
                            cleanField();


                        } else {

                            Stage currentStage = (Stage) saveButton.getScene().getWindow();
                            currentStage.close();
                            cleanField();
                            treatmentController.loadTreatmentTable();
                        }


                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save treatment!", ButtonType.OK).show();
                    }

                ////////////////////// Update Treatment ////////////////////////////
                }else{

                    TreatmentDTO treatmentDTO = new TreatmentDTO(id , name, type, description, Double.parseDouble(price));
                    boolean result = treatmentModel.updateTreatment(treatmentDTO);

                    if (result) {
                        treatmentController.loadTreatmentTable();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Treatment successfully Updated . Would you like to update again?");

                        ButtonType buttonYes = new ButtonType("Yes");
                        ButtonType buttonNo = new ButtonType("No");
                        alert.getButtonTypes().setAll(buttonYes, buttonNo);

                        Optional<ButtonType> results = alert.showAndWait();


                        if (results.isPresent() && results.get() == buttonYes) {



                        } else {

                            Stage currentStage = (Stage) saveButton.getScene().getWindow();
                            currentStage.close();
                            cleanField();
                            treatmentController.loadTreatmentTable();
                        }


                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to update treatment!", ButtonType.OK).show();
                    }


                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField( int tId , String tName ,String type ,  String Description , double Price) {

        id = tId;
        tNameField.setText(tName);
        tTypeField.setText(type);
        tDescriptionField.setText(Description);
        tPriceField.setText(String.valueOf(Price ));
    }

    @FXML
    private void cleanField(){
        tNameField.setText("");
        tTypeField.setText("");
        tDescriptionField.setText("");
        tPriceField.setText("");

    }
}
