package com.example.ungdungnghenhac;

public class Song {
    public String Title;
    private int File;

    public Song(String title, int file){
        Title = title;
        File = file;
    }
    public String getTitle(){
        return Title;
    }

    public int getFile() {
        return File;
    }
}
