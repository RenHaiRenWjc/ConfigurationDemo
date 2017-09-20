package com.example.autodimen;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;


public class Main {
    public static void main(String[] args) {
        DimensParser parser = new DimensParser();
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please Input Folder Path (Example: D:\\xx\\app\\src\\main\\res): ");
            if (scan.hasNext()){
                String path = scan.next();
                File file = new File(path);
                if (file != null && file.exists() && file.isDirectory()){
                    if (path.endsWith("res")){
                        Config.path = path;
                    }else {
                        System.out.println("Folder Path Input Error, Quit!!!");
                        return;
                    }
                }else {
                    System.out.println("Folder Path Input Error, Quit!!!");
                    return;
                }
            }else {
                System.out.println("Not found Input, Quit!!!");
                return;
            }


            int dp_max, sp_max;

            Scanner dpScan = new Scanner(System.in);
            System.out.println("Please Input Dp max: ");
            if (dpScan.hasNext()){
                dp_max = dpScan.nextInt();
                if (dp_max > 0){
                    Config.dp_max = dp_max;
                }
            }else {
                System.out.println("Not found Input, Quit!!!");
                return;
            }

            Scanner spScan = new Scanner(System.in);
            System.out.println("Please Input Sp max: ");
            if (spScan.hasNext()){
                sp_max = spScan.nextInt();
                if (sp_max > 0){
                    Config.sp_max = sp_max;
                }
            }else {
                System.out.println("Not found Input, Quit!!!");
                return;
            }

            System.out.println("=============== Create Default Value ===============");
            DimensMaster master = new DimensMaster(Config.path);
            master.createFile();

            System.out.println("=============== Read dimens Info ===============");
            String path = Config.path + File.separator + "values-mdpi";
            //get the dimens file values to list
            List<DimenValues> list = parser.parse(new FileInputStream(path + File.separator + "dimens.xml"));
            System.out.println("Dimens Info List size: " + list.size());

            System.out.println("=============== Create dimens File ===============");
            //create dimens file
            DimensCreator creator = new DimensCreator(path, list);
            //批量生产dimens
            creator.createFile(Config.FOLDER_LDPI);
            creator.createFile(Config.FOLDER_HDPI);
            creator.createFile(Config.FOLDER_XHDPI);
            creator.createFile(Config.FOLDER_XXHDPI);
            creator.createFile(Config.FOLDER_XXXHDPI);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}