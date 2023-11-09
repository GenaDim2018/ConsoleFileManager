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
            String[] commArgs = new String[2];
            if (command.contains(" ")) {
                commArgs[0] = command.substring(0, command.indexOf(" "));
                commArgs[1] = command.substring(command.indexOf(" ")+1);
            } else commArgs[0] = command;
            switch (commArgs[0]) {
                case ("ls") -> {
                    if (commArgs[1] == null) executor.ls();
                    else if (commArgs[1].equals("-l")) executor.lsInfo();
                    else System.out.println(unknownCommand);
                }
                case ("cd") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else {
                        if (commArgs[1].equals("..")) {
                            executor.cdUp();
                        } else executor.cd(commArgs[1]);
                    }
                }
                case ("mkdir") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else executor.mkdir(commArgs[1]);
                }
                case ("rm") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else executor.rm(commArgs[1]);
                }
                case ("exit") -> {
                    if (commArgs[1] == null) System.exit(1);
                    else System.out.println(unknownCommand);
                }
                default -> System.out.println(unknownCommand);
            }
        }
    }

    public Executor getExecutor() {
        return executor;
    }

}
