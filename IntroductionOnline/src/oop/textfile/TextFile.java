package oop.textfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TextFile extends AbstractFile {
    public Directory dir;

    public TextFile(String path, String name) {
        super(path+name);
        dir = new Directory(path);
    }

    public boolean createFile() throws IOException {
        if (!exist() && this.dir.exist()) {
           return path.createNewFile();
        }
        return false;
    }

    public void print() throws IOException {
        if (exist()) {
            List<String> strings = Files.readAllLines(path.toPath());
            if (strings.size() == 0) {
                System.out.println("File empty");
            } else {
                strings.forEach(System.out::println);
            }

        }
    }

    public boolean addText(String s) throws IOException {
        if (exist()) {
            try {
                Files.write(path.toPath(), s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {

            }
        }
        return false;

    }

}
