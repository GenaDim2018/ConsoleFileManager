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
                commArgs[1] = command.substring(command.indexOf(" ") + 1);
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
                case ("mv") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else {
                        if (commArgs[1].contains(" ") && commArgs[1].contains("\\")) {
                            int regexIndex = commArgs[1].lastIndexOf(" ", commArgs[1].lastIndexOf("\\"));
                            String destination = commArgs[1].substring(regexIndex + 1);
                            String fileName = commArgs[1].substring(0, regexIndex);
                            executor.mv(fileName, destination);
                        } else System.out.println(unknownCommand);
                    }

                }
                case ("cp") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else {
                        if (commArgs[1].contains(" ") && commArgs[1].contains("\\")) {
                            int regexIndex = commArgs[1].lastIndexOf(" ", commArgs[1].lastIndexOf("\\"));
                            String destination = commArgs[1].substring(regexIndex + 1);
                            String fileName = commArgs[1].substring(0, regexIndex);
                            executor.cp(fileName, destination);
                        } else System.out.println(unknownCommand);
                    }
                }
                case ("finfo") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                    else {
                        executor.finfo(commArgs[1]);
                    }
                }
                case ("find") -> {
                    if (commArgs[1] == null) System.out.println(unknownCommand);
                }
                //C:\Users\genad\Desktop\java
                case ("help") -> {
                    if (commArgs[1] == null) {
                        System.out.println("ls – распечатать список файлов текущего каталога");
                        System.out.println("ls -l – распечатать список файлов текущего каталога c информаций о файлах");
                        System.out.println("cd [имя директории] – переход в указанную поддиректорию");
                        System.out.println("cd .. – переход в родительский каталог");
                        System.out.println("mkdir [имя директории] – создание новой директории с указанным именем");
                        System.out.println("rm [имя файла/директории] – удаление указанного файла или директории");
                        System.out.println("mv [имя файла/директории] [путь/новое имя] – переименовать/перенести файл или директорию");
                        System.out.println("cp [имя файла] [путь копирования] – скопировать файл");
                        System.out.println("finfo [имя файла] – получить подробную информацию о файле");
                        System.out.println("help – вывод в консоль всех поддерживаемых команд");
                        System.out.println("exit – завершить работу");
                    } else System.out.println(unknownCommand);
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
