package oop.textfile;

import java.io.File;

public class Directory extends AbstractFile {

    public Directory(String path) {
        super(path);
    }

    public boolean create() {
        if (!exist()) {
            return path.mkdir();
        }
        return false;
    }

    public void print() {
        for (File file : path.listFiles()) {
            if(file.isFile()){
                System.out.println("File:" + file.getName());
            }else {
                System.out.println("Folder:" + file.getName());
            }
        }
    }
}
