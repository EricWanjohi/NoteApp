package ke.co.droidsense.noteapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ke.co.droidsense.noteapp.model.Note;
import ke.co.droidsense.noteapp.repository.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    //Member Variables...
    private LiveData<List<Note>> noteLiveData;
    private NoteRepository repository;


    //Constructor...
    public NoteViewModel(@NonNull Application application) {
        super( application );

        //Initialize ViewModel...
        initializeViewModel();
    }

    //Initialize ViewModel...
    private void initializeViewModel() {
        //Check if LiveData is Null.
        if (noteLiveData == null) {
            //Get Notes using the Repository...
            repository = NoteRepository.getNoteRepository( getApplication() );
            noteLiveData = repository.getNotesLiveData();
        }
    }

    /*______________________Handle Crud functionality...______________________________________*/

    //Insert New Note Item...
    public void addNote(Note note) {
        repository.insertNote( note );
    }

    //Delete Note Item...
    public void deleteNote(Note note) {
        repository.deleteNote( note );
    }

    //UpDate Note item...
    public void upDateNote(Note note) {
        repository.upDateNote( note );
    }

    //Get LiveData item.
    public LiveData<List<Note>> getNotesLiveData() {
        return noteLiveData;
    }
}
