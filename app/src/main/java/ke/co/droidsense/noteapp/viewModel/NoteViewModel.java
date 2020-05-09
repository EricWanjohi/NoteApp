package ke.co.droidsense.noteapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ke.co.droidsense.noteapp.model.Note;

public class NoteViewModel extends AndroidViewModel {

    //Member Variables...
    private LiveData<Note> noteLiveData;


    //Constructor...
    public NoteViewModel(@NonNull Application application) {
        super( application );
    }

    public LiveData<Note> getNotesLiveData() {
        return noteLiveData;
    }
}
