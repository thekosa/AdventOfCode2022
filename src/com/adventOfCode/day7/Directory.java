package com.adventOfCode.day7;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final List<File> files;
    private final List<Directory> subDirectories;
    private final Directory superDirectory;
    private int filesSize;
    private final String name;

    public Directory(String name, Directory superDirectory) {
        this.name = name;
        this.superDirectory = superDirectory;
        files = new ArrayList<>();
        subDirectories = new ArrayList<>();
        filesSize = 0;
    }

    public int getTotalSize() {
        int size = 0;
        if (this.isRoot()) {
            for (File file : files) {
                size += file.getSize();
            }
            return size;
        } else {
            for (Directory directory : subDirectories) {
                size += directory.getTotalSize();
            }
        }
        for (File file : files) {
            size += file.getSize();
        }
        return size;
    }

    public int getFilesSize() {
        return filesSize;
    }

    public void setFilesSize() {
        filesSize = 0;
        for (File file : files) {
            filesSize += file.getSize();
        }
    }

    public void newFile(File file) {
        files.add(file);
    }

    public void newFile(int size, String name) {
        files.add(new File(size, name, this));
        filesSize += size;
    }

    public void newSubDirectory(Directory directory) {
        subDirectories.add(directory);
    }

    public void newSubDirectory(String name) {
        subDirectories.add(new Directory(name, this));
    }

    public Directory getSubDirectory(String name) {
        for (Directory dir : subDirectories) {
            if (dir.getName().equals(name)) {
                return dir;
            }
        }
        return null;
    }

    public List<Directory> getSubDirectories() {
        return subDirectories;
    }

    public String getName() {
        return name;
    }

    public boolean isStartOfBranch() {
        return superDirectory == null;
    }

    public boolean isRoot() {
        return subDirectories.isEmpty();
    }

    public Directory getSuperDirectory() {
        return superDirectory;
    }

    public String ls() {
        StringBuilder stringBuilder = new StringBuilder();
        subDirectories.forEach(dir -> stringBuilder
                .append("level:")
                .append(dir.getLevel())
                .append("dir\t")
                .append(dir.getName())
                .append("\n"));
        stringBuilder.append(lsFiles());
        return stringBuilder.toString();
    }

    public String lsFiles() {
        StringBuilder stringBuilder = new StringBuilder();
        files.forEach(file -> stringBuilder
                .append("\t".repeat(file.getLevel()))
                .append("level:")
                .append(file.getLevel())
                .append(" file\t")
                .append(file.getName())
                .append("\t")
                .append(file.getSize())
                .append("\n"));
        return stringBuilder.toString();
    }

    public int getLevel() {
        if (this.isStartOfBranch()) {
            return 0;
        } else {
            return superDirectory.getLevel() + 1;
        }
    }
}
