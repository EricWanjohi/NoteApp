package ke.co.droidsense.noteapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import ke.co.droidsense.noteapp.model.Note;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {

    //Query All Notes from NoteDb...
    @Query("SELECT * FROM Note")
    LiveData<Note> getAllNotes();

    //Insert Note Item to NoteDb...
    @Insert(onConflict = REPLACE)
    void addNoteItem(Note note);

    //Delete Note Item From NoteDb...
    @Delete
    void deleteNoteItem(Note note);
}
