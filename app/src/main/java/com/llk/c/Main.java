package com.llk.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * (: Author：liangkai
 * (: WorkSpace: TAS
 * (: CreateDate: 2017/9/5
 * (: Describe:
 */

public class Main {
    public static String project_res_path = "D:\\TAS\\WeCar-Navi\\navisdk\\src\\main\\res";

    public static String image_path = "";

    public static String s600 = "drawable-sw600dp";
    public static String s400 = "drawable-sw400dp";
    public static String s720 = "drawable-sw720dp";
    public static String sXXh = "drawable-xxhdpi";

    public static void main(String[] args){
        File imageFolder = new File(image_path);

        if (imageFolder != null && imageFolder.exists() && imageFolder.isDirectory()){
            File[] files = imageFolder.listFiles();
            for (File f : files){
                if (f != null && !f.isDirectory()){
                    String fileName = f.getName();
                    if (fileName.endsWith("@0.75x.png")){
                        copyFile(image_path + File.separator + fileName,
                                project_res_path + File.separator + s400);
                        rName(project_res_path + File.separator + s400 + File.separator + fileName);
                    }else if (fileName.endsWith("@1.25x.png")){
                        copyFile(image_path + File.separator + fileName,
                                project_res_path + File.separator + s720);
                        rName(project_res_path + File.separator + s720 + File.separator + fileName);
                    }else if (fileName.endsWith("@1.5x.png")){
                        copyFile(image_path + File.separator + fileName,
                                project_res_path + File.separator + sXXh);
                        rName(project_res_path + File.separator + sXXh + File.separator + fileName);
                    }
//                    else if (fileName.startsWith("sdk_rd_ic_turn_")){
//                        rName2(image_path + File.separator + fileName);
//                        copyFile(image_path + File.separator + fileName,
//                                project_res_path + File.separator + sXXh);
//                    }
                }
            }
        }
    }

    private static void rName2(String path){
        File file = new File(path);
        String name = file.getName();
        String str = name.replace("rd", "rg");
        file.renameTo(new File(file.getParent() + File.separator + str));
    }

    private static void rName(String path){
        File file = new File(path);
        String name = file.getName();
        String str = "";
        if (name.endsWith("@0.75x.png")){
            str = name.replace("@0.75x.png", ".png");

        }else if (name.endsWith("@1.25x.png")){
            str = name.replace("@1.25x.png", ".png");

        }else if (name.endsWith("@1.5x.png")){
            str = name.replace("@1.5x.png", ".png");

        }

        file.renameTo(new File(file.getParent() + File.separator + str));
    }

    /**
     * 复制文件
     *
     * @param srcPath
     *            源文件绝对路径
     * @param destDir
     *            目标文件所在目录
     * @return boolean
     */
    private static boolean copyFile(String srcPath, String destDir) {
        boolean flag = false;

        File srcFile = new File(srcPath);
        if (!srcFile.exists()) { // 源文件不存在
            System.out.println("源文件不存在");
            return false;
        }
        // 获取待复制文件的文件名
        String fileName = srcPath
                .substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        if (destPath.equals(srcPath)) { // 源文件路径和目标文件路径重复
            System.out.println("源文件路径和目标文件路径重复!");
            return false;
        }
        File destFile = new File(destPath);
        if (destFile.exists() && destFile.isFile()) { // 该路径下已经有一个同名文件
            System.out.println("目标目录下已有同名文件!");
            return false;
        }

        File destFileDir = new File(destDir);
        destFileDir.mkdirs();
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(srcPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();

            flag = true;
        } catch (IOException e) {
        }

        if (flag) {
            System.out.println("复制文件成功!");
        }

        return flag;
    }
}
