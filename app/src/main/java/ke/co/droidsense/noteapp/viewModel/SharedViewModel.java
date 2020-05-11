package ke.co.droidsense.noteapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    //Member Variables...
    private MutableLiveData<CharSequence> noteTitleMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CharSequence> noteDescMutableLiveData = new MutableLiveData<>();

    //Create a method to Edit the Note object title...
    public void editNoteTitle(CharSequence note_title) {

        //Perform text update.
        noteTitleMutableLiveData.setValue( note_title );
    }

    public void editNoteDesc(CharSequence note_desc) {

        //Perform text update.
        noteDescMutableLiveData.setValue( note_desc );
    }

    public LiveData<CharSequence> getNoteTitleMutableLiveData() {
        return noteTitleMutableLiveData;
    }

    public LiveData<CharSequence> getNoteDescMutableLiveData() {
        return noteDescMutableLiveData;
    }


}
