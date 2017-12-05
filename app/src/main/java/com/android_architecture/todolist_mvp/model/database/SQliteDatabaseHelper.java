package com.android_architecture.todolist_mvp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Silent on 12/3/2017.
 */

public class SQliteDatabaseHelper extends SQLiteOpenHelper {

    private static SQliteDatabaseHelper mSQliteDatabaseHelper;

    private SQliteDatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {

    }

    public static synchronized SQliteDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mSQliteDatabaseHelper == null) {
            mSQliteDatabaseHelper = new SQliteDatabaseHelper(context.getApplicationContext());
        }
        return mSQliteDatabaseHelper;
    }

}
