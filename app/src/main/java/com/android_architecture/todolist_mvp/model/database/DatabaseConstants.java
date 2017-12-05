package com.android_architecture.todolist_mvp.model.database;

/**
 * Created by Silent on 12/3/2017.
 */

public class DatabaseConstants {

    public static final String DATABASE_NAME = "notes_db.sqlite";

    public static final String TABLE_NAME = "tbl_notes";

    // TABLE NOTES COLUMNS
    public static final String TABLE_NOTES_COLUMN_ID = "_Id";
    public static final String TABLE_NOTES_COLUMN_NOTE = "note";
    public static final String TABLE_NOTES_COLUMN_DATE = "created_date";

    // TABLE VERSION
    public static final int DATABASE_CURRENT_VERSION = 1;

    // Change in case of new build
    public static final int DATABASE_OLDER_VERSION = 1;

    // SQL CREATE TABLE
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + TABLE_NOTES_COLUMN_ID + " INTEGER AUTO INCREMENT PRIMARY KEY, " +
            TABLE_NOTES_COLUMN_NOTE + " TEXT, " + TABLE_NOTES_COLUMN_DATE + " TEXT);";


    public static final String TABLE_DELETE_CLAUSE = DatabaseConstants.TABLE_NOTES_COLUMN_ID + "=?";

    // Exceptions TAG
    public static final String DATABASE_EXCEPTION = "SQliteDatabaseHelper_Exception";

    // Messages
    public static final String DATABASE_RECORD_INSERTED_SUCCESSFULLY = "Note inserted successfully";
    public static final String DATABASE_RECORD_INSERTED_FAILED = "Note insertion failed";

    public static final String DATABASE_RECORD_REMOVED_SUCCESSFULLY = "Note deleted successfully";
    public static final String DATABASE_RECORD_REMOVED_FAILED = "Note delete failed";


}
