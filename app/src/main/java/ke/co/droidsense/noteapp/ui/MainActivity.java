package ke.co.droidsense.noteapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import ke.co.droidsense.noteapp.R;
import ke.co.droidsense.noteapp.adapter.NoteAdapter;
import ke.co.droidsense.noteapp.model.Note;
import ke.co.droidsense.noteapp.viewModel.NoteViewModel;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    //Member Variables...
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private NoteViewModel noteViewModel;
    private LinearLayoutManager linearLayoutManager;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        //Initializations...
        Timber.plant( new Timber.DebugTree() );
        noteList = new ArrayList<>();

        //Set up RecyclerView...
        setUpRecyclerView();

        //Set up ViewModel...
        setUpViewModel();
    }

    //ViewModel Configs...
    private void setUpViewModel() {
        //Get ViewModel...
        noteViewModel = ViewModelProviders.of( this ).get( NoteViewModel.class );
        noteViewModel.getNotesLiveData().observe( this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                //Log Note data...
                Timber.e( note.getTitle() );
                Timber.e( note.getDescription() );
            }
        } );
    }

    //Recycler View Configs...
    private void setUpRecyclerView() {
        //Check if adapter is null...
        if (noteAdapter == null) {
            //Create new Instance...
            recyclerView = findViewById( R.id.recyclerView );
            linearLayoutManager = new LinearLayoutManager( this );
            noteAdapter = new NoteAdapter( noteList, this );
            noteAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager( linearLayoutManager );
            recyclerView.setAdapter( noteAdapter );
            recyclerView.setHasFixedSize( true );
        } else {
            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.
            Toasty.success( this, "Transitioning to Settings Activity" );
            return true;
        } else if (id == R.id.action_new_note) {
            //Toast.
            Toasty.success( this, "Transitioning to New Note Activity" );
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}
