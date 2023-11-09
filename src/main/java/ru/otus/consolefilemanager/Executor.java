package ru.otus.consolefilemanager;


import java.io.*;
import java.util.Date;

import static ru.otus.consolefilemanager.Manager.readLine;

public class Executor {

    private File filePath;

    public File getFilePath() {
        return filePath;
    }


    public Executor(String directory) throws IOException {
        filePath = new File(directory);
        if (checkDirectory(filePath)) System.out.println(filePath.getCanonicalFile());
        else System.out.println("Некорректный путь");

    }

    public void ls() {
        String[] strList = filePath.list();
        if (strList.length == 0) System.out.println("Пустая директория");
        else for (String str : strList) System.out.println(str);
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
        if (!newPath.exists()) {
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

    public void mkdir(String name) {
        File newDir = new File(filePath.getPath() + "/" + name);
        if (newDir.mkdir()) System.out.println("Директория " + name + " успешно создана");
        else System.out.println("Некорректное имя директории");
    }

    public void rm(String name) {
        File rmFile = new File(filePath.getPath() + "/" + name);
        if (checkDirectory(rmFile)) {
            if (rmFile.isFile()) {
                if (rmFile.delete()) System.out.println(name + " успешно удален");
                else System.out.println("Не удалось удалить файл, возможно сейчас он открыт");
            } else {
                if (rmFile.delete()) System.out.println("Директория успешно удалена");
                else {
                    System.out.println("Директория не пуста, удалить? (Да/Нет)");
                    if (readLine().equals("Да")) {
                        if (deleteDirectory(rmFile)) System.out.println("Директория успешно удалена");
                        else
                            System.out.println("Не удалось полностью удалить директорию, возможно сейчас открыт файл из нее");
                    }
                }
            }
        } else System.out.println("Неверное имя файла/директории");
    }

    public boolean deleteDirectory(File directory) {
        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file);
            }
        }
        return directory.delete();
    }

    public void mv(String name, String destination) {
        File mvFile = new File(filePath.getPath() + "/" + name);
        File dest = new File(destination);
        File mv = new File(destination + "/" + name);
        if (checkDirectory(mvFile) && checkDirectory(dest)) {
            mvFile.renameTo(mv);
            System.out.println("Файл/директория успешно перемещена");
        } else System.out.println("Некорректное имя файла/директории или путь");
    }

    public void cp(String name, String destination) {
        File cpFile = new File(filePath.getPath() + "/" + name);
        File dest = new File(destination);
        if (checkDirectory(cpFile) && checkDirectory(dest) && cpFile.isFile()) {
            File newFile = new File(dest.getPath() + "/" + name);
            try {
                if (!newFile.createNewFile()) System.out.println("Файл с таким именем уже существует");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (InputStream in = new FileInputStream(cpFile); OutputStream out = new FileOutputStream(newFile)) {
                newFile.createNewFile();
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buf)) > 0) {
                    out.write(buf, 0, bytesRead);
                }
                System.out.println("Скопировано успешно");
            } catch (IOException e) {
                System.out.println("Не удалось скопировать");
                e.printStackTrace();
            }
        } else System.out.println("Некорректное имя файла/директории или путь");
    }

    public void finfo(String name) {
        File file = new File(filePath.getPath() + "/" + name);
        if (checkDirectory(file) && file.isFile()) {
            Date date = new Date(file.lastModified());
            System.out.println(file.getName() + " " + file.length() + " Б, последнее изменение " + date);
        } else System.out.println("Неверное имя файла");
    }

    public boolean checkDirectory(File dir) {
        return dir.exists();
    }

}
