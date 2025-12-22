package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lk.ijse.ayurvediccenter.dto.MedicineDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.model.MedicineModel;

import java.util.Optional;

public class AddMedicineController {
    private MedicineController medicineController;

    @FXML
    private TextArea medDescriptionField;

    @FXML
    private TextArea medNameField;

    @FXML
    private TextArea medPriceField;

    @FXML
    private TextArea medQtyField;

    @FXML
    private Button saveButton;

    private final String MEDICINE_ID_REGEX = "^[0-9]+$";
    private final String MEDICINE_NAME_REGEX = "^[A-Za-z]+ [A-Za-z]+$";
    private final String MEDICINE_QTY_REGEX =  "^[0-9]+$";
    private final String MEDICINE_DESCRIPTION_REGEX = "^(?:(?:\\S+\\s+){0,299}\\S+)?\\s*$";
    private final String MEDICINE_PRICE_REGEX = "^\\d+(\\.\\d{1,2})?$";

    MedicineModel medicineModel = new MedicineModel();

    public void setMedicineController(MedicineController medicineController) {
        this.medicineController = medicineController;
    }

    @FXML
    private void handleSaveMedicine(){
        String name = medNameField.getText();
        String description = medDescriptionField.getText();
        String qty = medQtyField.getText();
        String price = medPriceField.getText();



        if(!name.matches(MEDICINE_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name format!", ButtonType.OK).show();
        }else if(!description.matches(MEDICINE_DESCRIPTION_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid description format!", ButtonType.OK).show();
        }else if(!qty.matches(MEDICINE_QTY_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty format!", ButtonType.OK).show();
        }else if(!price.matches(MEDICINE_PRICE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid price format!", ButtonType.OK).show();
        }else{
            try{
                MedicineDTO medicineDTO = new MedicineDTO(name, description, Integer.parseInt(qty), Double.parseDouble(price));
                boolean result = medicineModel.saveMedicine(medicineDTO);

                if(result){
                    medicineController.loadMedicineTable();
                    Alert alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Medicine successfully added. Would you like to add another Medicine?");

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
                        medicineController.loadMedicineTable();
                    }



                }else {
                    new Alert(Alert.AlertType.ERROR,"Failed to save Medicine!", ButtonType.OK).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void cleanField(){
        medNameField.setText("");
        medDescriptionField.setText("");
        medQtyField.setText("");
        medPriceField.setText("");

    }
}

