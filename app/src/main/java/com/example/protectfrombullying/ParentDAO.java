package com.example.protectfrombullying;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ParentDAO {
    @Query("SELECT * FROM Parent")
    List<Parent> getAll();

    @Insert
    void insert(Parent parent);

    @Delete
    void delete(Parent parent);

    @Query("DELETE FROM Parent")
    void deleteAll();

    @Update(onConflict = REPLACE)
    void updateParentEmailId(Parent parent);
}
