package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.dto.MedicineDTO;
import lk.ijse.ayurvediccenter.dto.TreatmentDTO;
import lk.ijse.ayurvediccenter.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineModel {

    public boolean saveMedicine (MedicineDTO medicineDTO) throws SQLException {

        boolean result =
             CrudUtil.execute(
                     "INSERT INTO Medicine ( med_name ,description , stock_qty , unit_price ) VALUES (?,?,?,?)",
                     medicineDTO.getName(),
                     medicineDTO.getDescription(),
                     medicineDTO.getQty(),
                     medicineDTO.getPrice()
             );
        return result;
    }

    public boolean updateMedicine(MedicineDTO medicineDTO) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "UPDATE Medicine SET med_Name=? ,description=? ,stock_qty=? ,unit_price=?  WHERE med_id=? ",
                        medicineDTO.getName(),
                        medicineDTO.getDescription(),
                        medicineDTO.getQty(),
                        medicineDTO.getPrice(),
                        medicineDTO.getMed_id()
                );
        return result;
    }

    public boolean deleteMedicine(String id) throws SQLException {
        boolean result =
                CrudUtil.execute(
                        "DELETE FROM Medicine  WHERE med_id=? ",
                        id
                );
        return result;
    }

    public MedicineDTO searchMedicine(String id) throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Medicine WHERE med_id=? ",
                        id
                );

        if (rs.next()){
            int med_id = rs.getInt("med_id");
            String name = rs.getString("med_name");
            String description = rs.getString("description");
            int qty = rs.getInt("stock_qty");
            double price = rs.getDouble("unit_price");


            return new MedicineDTO(med_id,name,description,qty,price);
        }else {
            return null;
        }
    }

    public MedicineDTO searchTreatmentByName(String name) throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Medicine WHERE med_name=?  ",
                        name
                );

        if (rs.next()){
            int med_id = rs.getInt("med_id");
            String med_name = rs.getString("med_name");
            String description = rs.getString("description");
            int qty = rs.getInt("stock_qty");
            double price = rs.getDouble("unit_price");


            return new MedicineDTO(med_id,med_name,description,qty,price);
        }else {
            return null;
        }
    }

    public List<MedicineDTO> getMedicine() throws SQLException {

        ResultSet rs =
                CrudUtil.execute(
                        "SELECT * FROM Medicine ORDER BY med_id DESC"
                );

        List<MedicineDTO> medicineList = new ArrayList<>();

        while (rs.next()) {
            MedicineDTO medicineDTO = new MedicineDTO(
                    rs.getInt("med_id"),
                    rs.getString("med_name"),
                    rs.getString("description"),
                    rs.getInt("stock_qty"),
                    rs.getDouble("unit_price")
            );
            System.out.println(medicineDTO);
            medicineList.add(medicineDTO);
        }

        return medicineList;

    }


//    this Method will get Total number of medQty
    public int getTotalMedQty() throws SQLException {
        ResultSet rs =
                CrudUtil.execute(
                        "SELECT SUM(stock_qty) AS total_quantity FROM Medicine"
                );

        if (rs.next()){
            int medQty = rs.getInt("total_quantity");


            return medQty ;
        }else {
            return 0;
        }
    }

//    this Method will get number of medicines items that qty below 50
    public int getLowMedQty() throws SQLException {
        String sql = "SELECT COUNT(med_id) AS total_quantity FROM Medicine WHERE stock_qty < 50";
        ResultSet rs = CrudUtil.execute(sql);

        if (rs.next()){
            int medQty = rs.getInt("total_quantity");

            return medQty ;
        }else {
            return 0;
        }
    }

// this Method will decrease med qty the medicine according to the prescription
    public boolean decreaseMedQty (int medId , int qty)throws SQLException{

        boolean isUpdated = CrudUtil.execute(
                "UPDATE Medicine SET stock_qty=stock_qty-? WHERE med_id =?",
                qty,
                medId
        );
        return isUpdated;

    }

//    this Method will check whether medicine is existing in the database
    public boolean isMedExists(String medName) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT med_id FROM Medicine WHERE med_name = ?",
                medName
        );
        return rs.next(); // true if patient exists
    }
}

