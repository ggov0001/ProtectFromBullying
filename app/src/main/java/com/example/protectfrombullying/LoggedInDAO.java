package com.example.protectfrombullying;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LoggedInDAO {

    @Query("SELECT * FROM LoggedIn")
    List<LoggedIn> getAll();

    @Insert
    void insert(LoggedIn loggedInDetails);

    @Delete
    void delete(LoggedIn loggedInDetails);

    @Query("DELETE FROM LoggedIn")
    void deleteAll();
}
