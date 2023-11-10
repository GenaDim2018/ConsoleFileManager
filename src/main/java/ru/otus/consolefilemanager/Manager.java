package ru.otus.consolefilemanager;

import java.io.IOException;
import java.util.Scanner;

public class Manager {
    private static Scanner sc = new Scanner(System.in);

    public static void run() {
        try {
            Parser parser;
            while (true) {
                System.out.println("Укажите директорию");
                String dir = readLine();
                parser = new Parser(new Executor(dir));
                if (parser.getExecutor().checkDirectory(parser.getExecutor().getFilePath())) break;
            }
            while (true) {
                parser.parse(readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLine() {
        return sc.nextLine();
    }
}
