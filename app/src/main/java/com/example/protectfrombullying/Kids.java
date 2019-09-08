package com.example.protectfrombullying;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Kids {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "kid_id")
    public String kidId;

    @ColumnInfo(name = "kid_name")
    public String kidName;

    public Kids() {
    }

    public Kids(String kidId, String kidName) {
        this.kidId = kidId;
        this.kidName = kidName;
    }

    public String getKidId() {
        return kidId;
    }

    public void setKidId(String kidId) {
        this.kidId = kidId;
    }

    public String getKidName() {
        return kidName;
    }

    public void setKidName(String kidName) {
        this.kidName = kidName;
    }
}
