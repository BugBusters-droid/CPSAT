package helpers;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lib {

    public static String getCellValue(String xlPath, String sheet, int r, int c) {
        String value = "";
        try {
            FileInputStream fis = new FileInputStream(xlPath);
            Workbook wb = WorkbookFactory.create(fis);
            value = wb.getSheet(sheet).getRow(r).getCell(c).toString();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return value;
    }


    public static int getRowCount(String xlPath, String sheet) {
        int rc = 0;
        try {
            FileInputStream fis = new FileInputStream(xlPath);
            Workbook wb = WorkbookFactory.create(fis);
            rc = wb.getSheet(sheet).getLastRowNum();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return rc;
    }

    public static String isLinkBroken(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.connect();
            String responseMessage = connection.getResponseMessage();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            System.out.println("Response is :----> "+responseMessage);
            System.out.println("Response code is :---->"+responseCode);
            return String.valueOf(responseCode);
        } catch (Exception exp) {
            return exp.getMessage();
        }
    }
}
