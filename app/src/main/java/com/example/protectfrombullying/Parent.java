package com.example.protectfrombullying;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Parent {

    @PrimaryKey(autoGenerate = true)
    public int parentId;

    @ColumnInfo(name = "emailId")
    public String emailId;

    public Parent() {
    }

    public Parent(@NonNull int parentId, String emailId) {
        this.parentId = parentId;
        this.emailId = emailId;
    }

    @NonNull
    public int getParentId() {
        return parentId;
    }

    public void setParentId(@NonNull int parentId) {
        this.parentId = parentId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
