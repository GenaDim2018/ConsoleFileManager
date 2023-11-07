package ru.otus.consolefilemanager;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Executor {

    private File filePath;

    public Executor(String directory) throws IOException {
        filePath = new File(directory);
        if (checkDirectory()) System.out.println(filePath.getCanonicalFile());
        else System.out.println("Некорректный путь");

    }

    public void ls() {
        String[] strList = filePath.list();

        if (strList.length == 0) System.out.println("Пустая директория");

    }

    public void lsInfo() {
        File[] files = filePath.listFiles();

        if (files.length == 0) System.out.println("Пустая директория");

        else {
            for (File file : files) {
                Date date = new Date(file.lastModified());
                System.out.println(file.getName() + " " + file.length() + " Б, последнее изменение " + date);
            }
        }
    }


    public void cd(String directory) throws IOException {
        File newPath = new File(filePath.getPath() + "/" + directory);
        if (!newPath.exists()){
            System.out.println("Неверно указана директория");
            return;
        }
        if (newPath.isDirectory()) {
            filePath = newPath;
            System.out.println(filePath.getCanonicalFile());
        } else System.out.println("Не является директорией");
    }

    public void cdUp() throws IOException {
        if (filePath.getParentFile() == null) System.out.println("Эта директория корневая");
        else {
            filePath = filePath.getParentFile();
            System.out.println(filePath.getCanonicalFile());
        }
    }

    public void mkdir(String name){
        File newDir = new File(filePath.getPath()+"/"+name);
        try {
            newDir.mkdir();
        }
        catch (UnknownError e){
            System.out.println("Некорректное имя директории");
        }
    }
    public boolean checkDirectory(){
        return filePath.exists();
    }

}
