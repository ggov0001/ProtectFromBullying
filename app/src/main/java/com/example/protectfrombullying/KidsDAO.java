package com.example.protectfrombullying;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface KidsDAO {

    @Query("SELECT * FROM Kids")
    List<Kids> getAll();

    @Insert
    void insert(Kids yourKids);

    @Delete
    void delete(Kids yourKids);

    @Query("DELETE FROM Kids")
    void deleteAll();

    //Get number steps for a given user in a given time
    @Query("SELECT * FROM Kids k WHERE k.kid_name = :kidName")
    Kids findByName(String kidName);

}
