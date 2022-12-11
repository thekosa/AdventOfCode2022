package com.adventOfCode.day7;

public class File {
    private final int size;
    private final String name;
    private final Directory superDirectory;

    public File(int size, String name, Directory superDirectory) {
        this.size = size;
        this.name = name;
        this.superDirectory = superDirectory;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Directory getSuperDirectory() {
        return superDirectory;
    }

    public int getLevel() {
        return superDirectory.getLevel() + 1;
    }
}
