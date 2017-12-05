package com.android_architecture.todolist_mvp.presenter;

import android.content.Context;
import android.view.View;

import com.android_architecture.todolist_mvp.MVP_Interactor;
import com.android_architecture.todolist_mvp.model.POJO.Note;
import com.android_architecture.todolist_mvp.model.TodoModel;

import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * Created by Silent on 12/3/2017.
 */

public class MainPresenter implements MVP_Interactor.RequiredPresenterOps, MVP_Interactor.PresenterOps {

    private WeakReference<MVP_Interactor.RequiredViewOps> mViewOps;

    private MVP_Interactor.ModelOps mModelOps;

    private boolean mConfigChanging;

    private Context mContext;

    public MainPresenter(MVP_Interactor.RequiredViewOps mViewOps) {
        this.mViewOps = new WeakReference<MVP_Interactor.RequiredViewOps>(mViewOps);
        this.mModelOps = new TodoModel(this);
    }

    /*
    * Call from Activity onConfiguraton Change
    * */
    @Override
    public void onConfigurationChanged(MVP_Interactor.RequiredViewOps view) {
        this.mViewOps = new WeakReference<MVP_Interactor.RequiredViewOps>(view);
    }

    /*
    * Setting Application Context for Domain layer
    * */
    @Override
    public Context getApplicationContext() {
        return mViewOps.get().getApplicationContext();
    }

    /*
    * isChangingConfig state on Destroy
    * */
    @Override
    public void onDestroy(boolean isChangingConfig) {
        mViewOps = null;
        mConfigChanging = isChangingConfig;
        if ( !isChangingConfig ) {
            mModelOps.onDestroy();
        }
    }

    /*
    * Called by view for new note creation
    * */
    @Override
    public void newNote(String textNote) {
        Note note = new Note();
        note.setNote(textNote);
        note.setDate(new Date().toString());
        mModelOps.insertNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        mModelOps.removeNote(note);
    }

    @Override
    public void onNoteInsert(String msg) {
       mViewOps.get().showToast(msg);
    }

    @Override
    public void onNoteRemove(String msg) {
        mViewOps.get().showToast(msg);
    }

    @Override
    public void onError(String errorMsg) {
        mViewOps.get().showToast(errorMsg);
    }

}
