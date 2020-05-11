package ke.co.droidsense.noteapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.dmoral.toasty.Toasty;
import ke.co.droidsense.noteapp.R;
import ke.co.droidsense.noteapp.dao.NoteDao;
import ke.co.droidsense.noteapp.database.NoteDb;
import ke.co.droidsense.noteapp.fragment.EditNoteFragment;
import ke.co.droidsense.noteapp.model.Note;
import ke.co.droidsense.noteapp.viewModel.NoteViewModel;
import timber.log.Timber;

public class NoteDetailsActivity extends AppCompatActivity {
    //Member Variables...
    private Intent intent;
    private static String note_title;
    private String note_desc;
    private TextView noteTitle;
    private TextView noteDesc;
    private static NoteDb noteDb;
    private static NoteDao noteDao;
    private static Context context;
    private static Note noteToEdit;
    private static String upDatedTitle;
    private static String upDatedDesc;
    private NoteViewModel noteViewModel;
    private FragmentManager editNoteFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private EditNoteFragment editNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_note_details );

        //Initializations...
        noteTitle = findViewById( R.id.note_title );
        noteDesc = findViewById( R.id.note_desc );

        //Get Intent extras...
        intent = getIntent();
        note_title = intent.getStringExtra( "note_title" );
        note_desc = intent.getStringExtra( "note_desc" );

        //Get Specific Note...
        getDbAndNoteDao();

        //Set TextView text...
        noteTitle.setText( note_title );
        noteDesc.setText( note_desc );

        //Init SharedViewModel.
        noteViewModel = ViewModelProviders.of( this ).get( NoteViewModel.class );
        noteViewModel.getNoteTitleMutableLiveData().observe( this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                noteTitle.setText( charSequence );
                upDatedTitle = charSequence.toString();
                Timber.e( upDatedTitle );
//                noteToEdit.setTitle(charSequence.toString());
            }
        } );
        noteViewModel.getNoteDescMutableLiveData().observe( this, new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                noteDesc.setText( charSequence );
                upDatedDesc = charSequence.toString();
                Timber.e( upDatedDesc );
//                noteToEdit.setDescription(charSequence.toString());
            }
        } );

        //Update...
        upDateNote( noteToEdit );
    }

    private void upDateNote(Note noteToEdit) {
        new upDateNote( noteToEdit );
    }

    private void getDbAndNoteDao() {
        new fetchDbAsyncTask();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //get menu inflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.note_details_menu, menu );

        //Find menu item
        MenuItem menuItem = menu.findItem( R.id.edit );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.edit:
                //Method to create Edit fragment...
                createEditFragment();
                break;
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected( item );
    }

    //Method to Create Edit Note Fragment...
    private void createEditFragment() {
        //
        Toasty.info( this, "Menu Clicked" ).show();
        editNoteFragmentManager = getSupportFragmentManager();
        fragmentTransaction = editNoteFragmentManager.beginTransaction();
        editNoteFragment = new EditNoteFragment();
        fragmentTransaction.add( R.id.edit_note_frag, editNoteFragment );
        fragmentTransaction.commit();
    }

    @WorkerThread
    private static class fetchDbAsyncTask extends AsyncTask<Void, Void, NoteDb> {
        @Override
        protected NoteDb doInBackground(Void... voids) {
            noteDb = NoteDb.getNoteDbInstance( context );
            noteDao = noteDb.getNoteDao();
            noteToEdit = noteDao.getNote( note_title );
            Timber.e( noteToEdit.toString() );
            return noteDb;
        }
    }

    @WorkerThread
    private static class upDateNote extends AsyncTask<Note, Void, Void> {
        //Member Variable...
        private Note note;

        //Construction...
        private upDateNote(Note noteToEdit) {
            this.note = noteToEdit;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            note.setTitle( upDatedTitle );
            note.setDescription( upDatedDesc );
            noteDao.updateNoteItem( note );
            return null;
        }
    }
}
