package ke.co.droidsense.noteapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ke.co.droidsense.noteapp.model.Note;
import ke.co.droidsense.noteapp.repository.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    //Member Variables...
    private MutableLiveData<CharSequence> noteTitleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CharSequence> noteDescMutableLiveData = new MutableLiveData<>();
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

    /*__________________ Methods to be Shared between activity and fragment... _________________**/

    //Create a method to Edit the Note object title...
    public void editNoteTitle(CharSequence note_title) {

        //Perform text update.
        noteTitleMutableLiveData.postValue( note_title );
    }

    //Create a method to Edit the Note object description...
    public void editNoteDesc(CharSequence note_desc) {

        //Perform text update.
        noteDescMutableLiveData.postValue( note_desc );
    }

    //Get Note Title MutableLiveData...
    public LiveData<CharSequence> getNoteTitleMutableLiveData() {
        return noteTitleMutableLiveData;
    }

    //Get Note Desc MutableLiveData...
    public LiveData<CharSequence> getNoteDescMutableLiveData() {
        return noteDescMutableLiveData;
    }
}
