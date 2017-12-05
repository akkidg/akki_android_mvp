package com.android_architecture.todolist_mvp.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android_architecture.todolist_mvp.MVP_Interactor;
import com.android_architecture.todolist_mvp.model.POJO.Note;
import com.android_architecture.todolist_mvp.model.database.SQliteDatabaseHelper;
import com.android_architecture.todolist_mvp.model.database.DatabaseConstants;

/**
 * Created by Silent on 12/3/2017.
 */

public class TodoModel implements MVP_Interactor.ModelOps {

    // Presenter reference
    private MVP_Interactor.RequiredPresenterOps mPresenter;

    // SQlite Instance
    private SQliteDatabaseHelper mSQliteDatabaseHelper;

    public TodoModel(MVP_Interactor.RequiredPresenterOps presenterOps) {
        mPresenter = presenterOps;
        mSQliteDatabaseHelper = SQliteDatabaseHelper.getInstance(mPresenter.getApplicationContext());
    }

    @Override
    public void insertNote(Note note) {
        try{
            mSQliteDatabaseHelper.getWritableDatabase();
            SQLiteDatabase database = mSQliteDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseConstants.TABLE_NOTES_COLUMN_NOTE,note.getNote());
            contentValues.put(DatabaseConstants.TABLE_NOTES_COLUMN_DATE,note.getDate());

            long recordId = database.insert(DatabaseConstants.TABLE_NAME,null,contentValues);
            mSQliteDatabaseHelper.close();

            note.set_ID(recordId);
            mPresenter.onNoteInsert(DatabaseConstants.DATABASE_RECORD_INSERTED_SUCCESSFULLY);
        }catch (Exception e){
            Log.e(DatabaseConstants.DATABASE_EXCEPTION,e.getMessage());
            mPresenter.onError(DatabaseConstants.DATABASE_RECORD_INSERTED_FAILED);
        }finally {
            mSQliteDatabaseHelper.close();
        }

    }

    @Override
    public void removeNote(Note note) {
        try {
            mSQliteDatabaseHelper.getWritableDatabase();
            SQLiteDatabase database = mSQliteDatabaseHelper.getWritableDatabase();
            int deletedRecord = database.delete(DatabaseConstants.TABLE_NAME, DatabaseConstants.TABLE_DELETE_CLAUSE,new String[]{String.valueOf(note.get_Id())});
            mSQliteDatabaseHelper.close();

            if(deletedRecord > 0){
                mPresenter.onNoteRemove(DatabaseConstants.DATABASE_RECORD_REMOVED_SUCCESSFULLY);
            }else{
                mPresenter.onError(DatabaseConstants.DATABASE_RECORD_REMOVED_FAILED);
            }
        }catch (Exception e){
            Log.e(DatabaseConstants.DATABASE_EXCEPTION,e.getMessage());
            mPresenter.onError(DatabaseConstants.DATABASE_RECORD_REMOVED_FAILED);
        }finally {
            mSQliteDatabaseHelper.close();
        }
    }

    @Override
    public void onDestroy() {
        if(mSQliteDatabaseHelper == null)
            return;
        mSQliteDatabaseHelper.close();
    }
}
