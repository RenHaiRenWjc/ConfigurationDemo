package com.example.dimen;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class DimensCreator {

    private List<DimenValues> valuesList;
    private String dimensPath;
    private String createFolder;

    private final String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
            + "<resources>\n";
    private final String xmlFooter = "</resources>";

    //unit
    private final String dip_unit = "dp";
    private final String sp_unit = "sp";
    //element
    private final String dipTemplate = "<dimen name=\"%s\">%s" + dip_unit + "</dimen>";
    private final String spTemplate = "<dimen name=\"%s\">%s" + sp_unit + "</dimen>";

    private final String DIR_TEMPLATE = "values-";

    public DimensCreator(String path, List<DimenValues> list) {
        dimensPath = path;
        valuesList = list;
    }

    public void createFile(String dpiLevel) {
        if (dpiLevel == null || dpiLevel.equals(""))return;

        String floderName = DIR_TEMPLATE + dpiLevel;

        String dir = dimensPath.replace("values-mdpi", "").trim() + File.separator + floderName;
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File file = new File(dirFile.getPath() + File.separator + "dimens.xml");
        float scale = Config.DPI_MAP.get(dpiLevel);
        createSingleFile(file, scale);

        System.out.println("Create " + dpiLevel + "(scale:"+ scale +")" + " File OK!!!");
    }


    private void createSingleFile(File file, float scale) {
        String data = xmlHeader;
        for (DimenValues values : valuesList) {
            String itemValue = null;
            String itemData = null;
            if (values.value.contains(dip_unit)) {
                float v = Float.parseFloat(values.value.replace(dip_unit, "").trim());
                itemValue = String.valueOf(v * scale);
                itemData = String.format(dipTemplate, values.name, itemValue) + "\n";
            }
            else if(values.value.contains(sp_unit)){
                float v = Float.parseFloat(values.value.replace(sp_unit, "").trim());
                itemValue = String.valueOf(v * scale);
                itemData = String.format(spTemplate, values.name, itemValue) + "\n";
            }
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
