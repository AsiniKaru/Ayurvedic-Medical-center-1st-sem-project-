package lk.ijse.ayurvediccenter.model;

import lk.ijse.ayurvediccenter.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ReportModel {

    public void printTSReport() throws JRException , SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/ayurvediccenter/reports/transaction.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObject);

        JasperPrint jp = JasperFillManager.fillReport(jr,null,conn);

        JasperViewer.viewReport(jp,false);
    }
}
