package com.example.protectfrombullying;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Kids.class}, version = 3, exportSchema = false)
public abstract class KidsDatabase extends RoomDatabase {

    public abstract KidsDAO kidsDAO();

    private static volatile KidsDatabase INSTANCE;

    static KidsDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (KidsDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), KidsDatabase.class, "Kids_Database").build();
                }
            }
        }
        return INSTANCE;
    }
}
