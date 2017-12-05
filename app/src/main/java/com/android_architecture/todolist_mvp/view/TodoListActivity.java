package com.android_architecture.todolist_mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android_architecture.todolist_mvp.MVP_Interactor;
import com.android_architecture.todolist_mvp.R;
import com.android_architecture.todolist_mvp.model.POJO.Note;
import com.android_architecture.todolist_mvp.presenter.MainPresenter;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity implements MVP_Interactor.RequiredViewOps{

    protected final String TAG = getClass().getSimpleName();

    // Presenter operations
    private MVP_Interactor.PresenterOps mPresenter;

    // Responsible to maintain the Objects state
    // during changing configuration
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMVPOps();
        setContentView(R.layout.activity_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Initialize and restart the Presenter.
     * This method should be called after {@link Activity#onCreate(Bundle)}
     */
    public void startMVPOps() {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() called for the first time");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() called more than once");
                reinitialize(this);
            }
        } catch ( InstantiationException | IllegalAccessException e ) {
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        }
    }

    /**
     * Initialize relevant MVP Objects.
     * Creates a Presenter instance, saves the presenter in {@link StateMaintainer}
     */
    private void initialize( MVP_Interactor.RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        mPresenter = new MainPresenter(view);
        mStateMaintainer.put(MVP_Interactor.PresenterOps.class.getSimpleName(), mPresenter);
    }

    /**
     * Recovers Presenter and informs Presenter that occurred a config change.
     * If Presenter has been lost, recreates a instance
     */
    private void reinitialize( MVP_Interactor.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {
        mPresenter = mStateMaintainer.get( MVP_Interactor.PresenterOps.class.getSimpleName() );

        if ( mPresenter == null ) {
            Log.w(TAG, "recreating Presenter");
            initialize( view );
        } else {
            mPresenter.onConfigurationChanged( view );
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        startMVPOps();
    }

    @Override
    public Context getApplicationContext(){
        return getApplicationContext();
    }

    @Override
    public void onRefreshNotesList() {

    }

    @Override
    public void initRecyclerView(ArrayList<Note> noteList) {

    }

    @Override
    public void updateRecyclerView(ArrayList<Note> noteList) {

    }

    /*
    * Show Toast
    * */
    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*
    * Show AlertDialog
    * */
    @Override
    public void showAlert(String msg) {

    }
}
