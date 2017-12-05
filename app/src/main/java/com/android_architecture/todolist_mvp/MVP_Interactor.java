package com.android_architecture.todolist_mvp;

import android.content.Context;

import com.android_architecture.todolist_mvp.model.POJO.Note;

import java.util.ArrayList;

/**
 * Created by Silent on 12/3/2017.
 */

public interface MVP_Interactor {

    /*
    * View methods available to Presenter
    * */
    interface RequiredViewOps{
        void showToast(String msg);
        void showAlert(String msg);
        Context getApplicationContext();
        void onRefreshNotesList();
        void initRecyclerView(ArrayList<Note> noteList);
        void updateRecyclerView(ArrayList<Note> noteList);
    }

    /*
    * Operations offered to view from presenter
    * */
    interface PresenterOps{
        void onConfigurationChanged(RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void newNote(String textNote);
        void deleteNote(Note note);
    }

    /*
    * Operations offered to presenter from model
    * */
    interface ModelOps{
        void insertNote(Note note);
        void removeNote(Note note);
        void onDestroy();
    }

    /*
    * Operations offered to model from presenter
    * */
    interface RequiredPresenterOps{
        Context getApplicationContext();
        void onNoteInsert(String msg);
        void onNoteRemove(String msg);
        void onError(String errorMsg);
    }

}
