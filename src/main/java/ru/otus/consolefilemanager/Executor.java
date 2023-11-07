package ru.otus.consolefilemanager;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Executor {
    private final String defaultDirectory = "src/main/resources/defaultDirectory/";
    private File filePath;

    public Executor(String directory) throws IOException {
        if (directory.isEmpty()) filePath = new File(defaultDirectory);
        else {
            filePath = new File(directory);
        }
        System.out.println(filePath.getCanonicalFile());
    }

    public void ls() {
        String[] strList = filePath.list();
        if (strList == null) System.out.println("Пустая директория");
        else for (String str : strList) {
            System.out.println(str);
        }
    }

    public void lsInfo() {
        File[] files = filePath.listFiles();
        if (files == null) System.out.println("Пустая директория");
        else {
            for (File file : files) {
                Date date = new Date(file.lastModified());
                System.out.println(file.getName() + " " + file.length() + " Б, последнее изменение " + date);
            }
        }
    }
}
