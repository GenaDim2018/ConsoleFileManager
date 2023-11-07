package ru.otus.consolefilemanager;

import java.io.IOException;
import java.util.Scanner;

public class Manager {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        try {
            Parser parser;
            do {
                System.out.println("Укажите директорию");
                parser = new Parser(new Executor(sc.nextLine()));
            } while (!parser.getExecutor().checkDirectory());
            while (true) {
                parser.parse(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}
