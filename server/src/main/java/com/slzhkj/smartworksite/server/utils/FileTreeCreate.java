package com.slzhkj.smartworksite.server.utils;

import java.io.File;

/**
 * 资源目录文档生成工具
 *
 * @author Yuezejian
 * @date 2020年 11月23日 08:48:17
 */
public class FileTreeCreate {
    public static void main(String[] args) {
        String path = "D:\\生物智联项目地址\\smartworksite";
        FileTreeCreate fileTreeCreate = new FileTreeCreate();
        try {
            fileTreeCreate.getFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getFile(String address) throws Exception {
        File file = new File(address);
        if(!file.exists()){
            throw new Exception("文件不存在:" + address);
        }
        createFileTree(file);
    }

    public void createFileTree(File fi){
        createFileTree(fi,0); // 默认根目录
    }

    public void createFileTree(File fi, int index) {
        File[] files = fi.listFiles();
        for(File file : files){
            if(file == null){
                continue;
            }
            printCon(file.getName(),index,file.isDirectory());
            if(file.listFiles() != null && file.listFiles().length > 0){
                createFileTree(file,index + 1 );
            }
        }
    }
    public static void printCon(String name,int index,boolean isDirectory){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0;i < index; i++){
            stringBuilder.append("  ");
        }
        if( isDirectory){
            stringBuilder.append("├──");
        } else {
            stringBuilder.append("└──");
        }
        stringBuilder.append(name);
        System.out.println(stringBuilder.toString());
        stringBuilder = null; // help gc
    }
}

