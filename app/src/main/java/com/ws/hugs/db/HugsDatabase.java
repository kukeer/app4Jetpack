package com.ws.hugs.db;

import android.content.Context;

import com.ws.hugs.data.db.MM131ArticleModel;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.db.mm131.ArticleDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {MM131ArticleModel.class},version = 1,exportSchema = false)
public abstract class HugsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME= "HUGS_DATABASE";

    private static HugsDatabase database ;

    public static synchronized HugsDatabase getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(
                    context.getApplicationContext(),
                    HugsDatabase.class,
                    DATABASE_NAME).build();
        }
        return database;
    }
    public abstract ArticleDao articleDao();
}
