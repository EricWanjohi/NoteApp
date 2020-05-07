package ke.co.droidsense.noteapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ke.co.droidsense.noteapp.dao.NoteDao;
import ke.co.droidsense.noteapp.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDb extends RoomDatabase {

    //Member Variables...
    private static final String Database = "NoteDb";
    private static NoteDb INSTANCE;

    //Instance getter...
    public static NoteDb getNoteDbInstance(Context context) {
        //Check if Instance is Null...
        if (INSTANCE == null) {
            //Create new NoteDb Instance...
            INSTANCE = Room.databaseBuilder( context, NoteDb.class, Database )
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    //Create a Dao object to fetch data from the NoteDb...
    public abstract NoteDao getNoteDao();
}
