package ke.co.droidsense.noteapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ke.co.droidsense.noteapp.dao.NoteDao;
import ke.co.droidsense.noteapp.database.NoteDb;
import ke.co.droidsense.noteapp.model.Note;

public class NoteRepository {
    private static NoteRepository noteRepository;
    //Member Variables...
    private NoteDb noteDb;
    private NoteDao noteDao;
    private LiveData<List<Note>> notesLiveData;

    //Constructor...
    private NoteRepository(Application application) {
        //Initialize Items...
        noteDb = NoteDb.getNoteDbInstance( application );
        noteDao = noteDb.getNoteDao();
        notesLiveData = noteDao.getAllNotes();
    }

    //Get Repository instance
    public static NoteRepository getNoteRepository(Application application) {
        if (noteRepository == null) {
            //Create new Instance.
            noteRepository = new NoteRepository( application );
        }
        return noteRepository;
    }

    //LiveData Object Getter...
    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    /*___________________Create all other methods to handle CRUD functionality.___________________*/

    //Insert New Note...
    public void insertNote(Note note) {
        new insertNoteAsyncTask( noteDao ).execute( note );
    }

    //Delete Note Item...
    public void deleteNote(Note note) {
        new deleteNoteAsyncTask( noteDao ).execute( note );
    }

    //UpDate Note Item...
    public void upDateNote(Note note) {
        new updateNoteAsyncTask( noteDao ).execute( note );
    }

    /*________________AsyncTask methods to handle CRUD in background threads...___________________*/

    //Insert Note AsyncTask
    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        //Variable
        private NoteDao noteDao;

        //Constructor...
        public insertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //Insert Note...
            noteDao.addNoteItem( notes[0] );
            return null;
        }
    }

    //Delete Note AsyncTask
    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        //Variable
        private NoteDao noteDao;

        //Constructor...
        public deleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //Delete Note...
            noteDao.deleteNoteItem( notes[0] );
            return null;
        }
    }

    //Update Note AsyncTask
    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        //Variable
        private NoteDao noteDao;

        //Constructor...
        public updateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //Insert Note...
            noteDao.updateNoteItem( notes[0] );
            return null;
        }
    }
}
