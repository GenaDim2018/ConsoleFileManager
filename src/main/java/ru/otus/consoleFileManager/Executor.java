package ru.otus.consoleFileManager;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Executor {
    private final String defaultDirectory = "src/main/java/ru/otus/consoleFileManager/defaultDirectory/";
    private File file;

    public Executor(String directory) throws IOException {
        if (directory.isEmpty()) file = new File(defaultDirectory);
        else{
            file = new File(directory);
        }
        System.out.println(file.getCanonicalFile());
    }

    public void ls() {
        String str = Arrays.toString(file.list()).replaceFirst("\\[|", "");
        str = str.substring(0,str.lastIndexOf("]")-1);
        System.out.println(str.replaceAll("\\[|\\]|\\,\\ ", "\n"));
    }

    public void lsInfo() {
        File [] files = file.listFiles();
        if (files == null) System.out.println("Пустая директория");
        else {
            for (File file : files) {
                Date date = new Date(file.lastModified());
                System.out.println(file.getName() +" "+file.length()+" Б, последнее изменение "+date);
            }
        }
    }
    public static void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Укажите директорию, для использования демонстрационной директории введите пустую строку");
        String dir = sc.nextLine();
        Parser parser = new Parser(new Executor(dir));
        while (true) {
            parser.parse(sc.nextLine());
        }
    }
}
