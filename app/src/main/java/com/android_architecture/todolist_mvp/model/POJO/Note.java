package com.android_architecture.todolist_mvp.model.POJO;

/**
 * Created by Silent on 12/3/2017.
 */

public class Note {

    private String note;
    private String date;
    private long _Id;

    public Note() { }

    public void set_ID(long id){
        _Id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public long get_Id(){
        return _Id;
    }
}
