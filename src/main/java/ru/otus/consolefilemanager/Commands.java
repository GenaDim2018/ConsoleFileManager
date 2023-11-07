package ru.otus.consolefilemanager;

public enum Commands {
    LS("ls"),
    CD("cd"),
    MKDIR("mkdir"),
    RM("rm"),
    MV("mv"),
    CP("cp"),
    FINFO("finfo"),
    HELP("help"),
    FIND("find"),
    EXIT("exit");
    private final String title;

    Commands(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

