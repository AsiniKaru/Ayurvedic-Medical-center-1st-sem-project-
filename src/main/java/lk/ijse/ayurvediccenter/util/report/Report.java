package lk.ijse.ayurvediccenter.util.report;

import lk.ijse.ayurvediccenter.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Report {

    public void printTSReport(String appDate) throws JRException , SQLException {

        Connection conn = DBConnection.getInstance().getConnection();
        //Step 01
        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/ayurvediccenter/reports/transaction.jrxml");
        //Step 02
        JasperReport jr = JasperCompileManager.compileReport(reportObject);
        //Step 03
        Map<String, Object> params = new HashMap<>();

        params.put("APP_DATE", java.sql.Date.valueOf(appDate));

        JasperPrint jp = JasperFillManager.fillReport(jr,params,conn);
        //Step 04
        JasperViewer.viewReport(jp,false);
    }
}
