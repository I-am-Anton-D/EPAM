package oop.textfile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AbstractFile {
    public File path;

    public AbstractFile(String path) {
        this.path = new File(path);
    }

    public boolean exist() {
        Path path = Paths.get(this.path.getAbsolutePath());
        return Files.exists(path);
    }

    public boolean rename(String name) {
        if (exist()) {
            File rename = new File(name);
            return path.renameTo(rename);
        }
        return false;
    }

    public boolean delete() {
        if (exist()) {
            return path.delete();
        }
        return false;
    }
}
