package com.llk.c;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView t0,t1,t2,t3,t4,t5,t6,t7,t8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t0 = (TextView) findViewById(R.id.t0);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);

        showConfig();
    }

    public void onBack(View view){
        finish();
    }

    public void showConfig(){
        t0.setText("获取屏幕信息" + "("+ Build.MODEL +")" + ": ");
        t1.setText("屏幕大小: " + getSizeName(this));
        t2.setText("屏幕密度: " + getDensity(this));
        t3.setText("屏幕方向: " + getOrientation(this));

        t4.setText("最小宽度: " + getResources().getConfiguration().smallestScreenWidthDp);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int h = metrics.heightPixels;
        int w = metrics.widthPixels;
        t5.setText("屏幕宽高: " + w + "x" + h);

        double diagonal = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
        int dens = metrics.densityDpi;
        double screenInches = diagonal/(double)dens;
        t6.setText("屏幕尺寸: " + screenInches);

        t7.setText("系统版本: " + android.os.Build.VERSION.SDK_INT);

        int screenLayout = getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_LONG_MASK;
        boolean isS = false;
        switch (screenLayout){
            case Configuration.SCREENLAYOUT_LONG_YES:
                isS = true;
                break;
            case Configuration.SCREENLAYOUT_LONG_NO:
                isS = false;
                break;
        }
        t8.setText("是否宽屏: " + isS);
    }

    private static String getOrientation(Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        switch (orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                return "land";
            case Configuration.ORIENTATION_PORTRAIT:
                return "port";
            default:
                return "undefined";
        }
    }

    private static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return "xlarge";
            default:
                return "undefined";
        }
    }

    private String getDensity(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;

        switch (density) {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }
    }
}
