package com.example.autodimen;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DimensMaster {

    private String dimensPath;

    private final String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
            + "<resources>\n";
    private final String xmlFooter = "</resources>";

    //unit
    private final String dip_unit = "dp";
    private final String sp_unit = "sp";
    //element
    private final String dipTemplate = "<dimen name=\"%s\">%s" + dip_unit + "</dimen>";
    private final String spTemplate = "<dimen name=\"%s\">%s" + sp_unit + "</dimen>";

    private final String item_dp_name = "auto_dimen_";
    private final String item_sp_name = "font_";

    public DimensMaster(String path) {
        dimensPath = path;
    }

    public void createFile() {
        String folderPath = dimensPath + File.separator + "values-mdpi";
        File dirFile = new File(folderPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File file = new File(dirFile.getPath() + File.separator + "dimens.xml");
        float scale = Config.DPI_MAP.get(Config.FOLDER_MDPI);
        createSingleFile(file, scale);

        System.out.println("Create " + Config.FOLDER_MDPI + " Default File OK!!!");
    }


    private void createSingleFile(File file, float scale) {
        String data = xmlHeader;

        data += "\n";
        if (Config.dp_max < 0 && Config.sp_max < 0){
            return;
        }

        for (int i=0; i <= Config.dp_max; i++) {
            String itemData = null;

            itemData = String.format(dipTemplate, item_dp_name + i, i) + "\n";
            data += itemData;
        }

        data += "\n";

        for (int i=0; i <= Config.sp_max; i++) {
            String itemData = null;

            itemData = String.format(spTemplate, item_sp_name + i, i) + "\n";
            data += itemData;
        }

        data += xmlFooter;

        FileOutputStream outputStream = null;
        ByteArrayInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            inputStream = new ByteArrayInputStream(data.getBytes());
            byte[] buffer = new byte[inputStream.available()];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
