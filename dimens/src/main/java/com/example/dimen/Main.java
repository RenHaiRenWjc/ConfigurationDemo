package com.example.dimen;

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
            String dimensPath = null;

            Scanner scan = new Scanner(System.in);
            System.out.println("Please Input Folder Path (Example: D:\\xx\\app\\src\\main\\res\\values-mdpi): ");
            if (scan.hasNext()){
                String path = scan.next();
                File file = new File(path);
                if (file != null && file.exists() && file.isDirectory()){
                    dimensPath = path + File.separator + "dimens.xml";

                    File f = new File(dimensPath);
                    if (f != null && f.exists()){
                        Config.path = path;
                    }else {
                        System.out.println("Not found dimens.xml, Quit!!!");
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

            System.out.println("=============== Read dimens Info ===============");
            //get the dimens file values to list
            List<DimenValues> list = parser.parse(new FileInputStream(dimensPath));
            System.out.println("Dimens Info List: " + list);

            System.out.println("=============== Create dimens File ===============");
            //create dimens file
            DimensCreator creator = new DimensCreator(Config.path, list);
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