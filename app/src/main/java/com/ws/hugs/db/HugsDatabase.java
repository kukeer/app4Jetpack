package com.ws.hugs.db;

import android.content.Context;

import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.db.mm131.ArticleDao;
import com.ws.hugs.db.mm131.VideoArticleDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MM131ArticleModel.class, MM131VideoArticleModel.class},version = 1,exportSchema = false)
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
    public abstract VideoArticleDao videoArticleDao();
}
