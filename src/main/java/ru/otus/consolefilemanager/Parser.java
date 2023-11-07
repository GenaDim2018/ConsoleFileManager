package ru.otus.consolefilemanager;


import java.io.IOException;

public class Parser {
    private final Executor executor;
    private final String unknownCommand = "Неизвестная команда";

    public Parser(Executor executor) {
        this.executor = executor;
    }

    public void parse(String command) throws IOException {
        if (command == null || command.isEmpty()) System.out.println("Введите команду");
        else {
            String[] commArgs = command.split(" ");
            switch (commArgs[0]) {
                case ("ls") -> {
                    if (commArgs.length == 1) executor.ls();
                    else if (commArgs[1].equals("-l") && commArgs.length == 2) executor.lsInfo();
                    else System.out.println(unknownCommand);
                }
                case ("cd") -> {
                    if (commArgs.length != 2) System.out.println(unknownCommand);
                    else {
                        if (commArgs[1].equals("..")) {
                            executor.cdUp();
                        } else executor.cd(commArgs[1]);
                    }
                }
                case ("mkdir") -> {
                    if (commArgs.length != 2) System.out.println(unknownCommand);
                    else executor.mkdir(commArgs[1]);
                }
                case ("exit") -> System.exit(1);
                default -> System.out.println(unknownCommand);
            }
        }
    }

    public Executor getExecutor() {
        return executor;
    }

}
