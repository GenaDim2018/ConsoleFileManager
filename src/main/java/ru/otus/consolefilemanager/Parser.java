package ru.otus.consolefilemanager;

public class Parser {
    private final Executor executor;
    public Parser(Executor executor){
        this.executor = executor;
    }
    public void parse(String command){
        if (command == null|| command.isEmpty()) System.out.println("Введите команду");
        else {
            String[] commArgs = command.split(" ");
            switch (commArgs[0]){
                case ("ls") -> {
                    if(commArgs.length==1) executor.ls();
                    else if (commArgs[1].equals("-l")&&commArgs.length==2)executor.lsInfo();
                    else System.out.println("Неизвестная команда");
                }
                case ("exit") -> System.exit(1);
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}
