package com.example.protectfrombullying;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LoggedIn {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int isParent;

    private int isCategoryChosen;

    public LoggedIn(int isParent, int isCategoryChosen) {
        this.isParent = isParent;
        this.isCategoryChosen = isCategoryChosen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public int getIsCategoryChosen() {
        return isCategoryChosen;
    }

    public void setIsCategoryChosen(int isCategoryChosen) {
        this.isCategoryChosen = isCategoryChosen;
    }
}
