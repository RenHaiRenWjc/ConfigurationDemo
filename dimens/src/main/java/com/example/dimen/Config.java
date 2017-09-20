package com.example.dimen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Config {

    /** folder template */
    public static final String FOLDER_LDPI = "ldpi";
    public static final String FOLDER_MDPI = "mdpi";
    public static final String FOLDER_HDPI = "hdpi";
    public static final String FOLDER_XHDPI = "xhdpi";
    public static final String FOLDER_XXHDPI = "xxhdpi";
    public static final String FOLDER_XXXHDPI = "xxxhdpi";

    public static final Map<String, Float> DPI_MAP = Collections.unmodifiableMap(new HashMap<String, Float>() {
        {
            put(FOLDER_LDPI, 0.75f);
            put(FOLDER_MDPI, 1.00f);
            put(FOLDER_HDPI, 1.50f);
            put(FOLDER_XHDPI, 2.00f);
            put(FOLDER_XXHDPI, 3.00f);
            put(FOLDER_XXXHDPI, 4.00f);
        }
    });

    public static String path = null;
}
