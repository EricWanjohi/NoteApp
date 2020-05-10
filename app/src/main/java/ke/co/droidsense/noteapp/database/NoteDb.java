package ke.co.droidsense.noteapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import ke.co.droidsense.noteapp.dao.NoteDao;
import ke.co.droidsense.noteapp.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDb extends RoomDatabase {

    //Member Variables...
    private static final String Database = "NoteDb";
    private static NoteDb INSTANCE;

    //Create method to populate noteDb with dummy data...
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate( db );
            //PopulateDb...
            new PopulateDbAsyncTask( INSTANCE ).execute();
        }
    };

    //Create a Dao object to fetch data from the NoteDb...
    public abstract NoteDao getNoteDao();

    //Instance getter...
    public static NoteDb getNoteDbInstance(Context context) {
        //Check if Instance is Null...
        if (INSTANCE == null) {
            //Create new NoteDb Instance...
            INSTANCE = Room.databaseBuilder( context, NoteDb.class, Database )
                    .fallbackToDestructiveMigration()
                    .addCallback( roomCallBack )
                    .build();
        }
        return INSTANCE;
    }

    //Create new AsyncTask to handle creation of dummy note items...
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        //Variables...
        private NoteDao noteDao;

        //Constructor...
        public PopulateDbAsyncTask(NoteDb noteDb) {
            noteDao = noteDb.getNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Add dummy Note items...
            noteDao.addNoteItem( new Note( "Note one title is long to test if view only displays one line for the title.", "Adds a dummy note to the application" ) );
            noteDao.addNoteItem( new Note( "Note Two", "Adds a second dummy note to the application" ) );
            noteDao.addNoteItem( new Note( "Note Three", "Adds a third dummy note to the application" ) );
            return null;
        }
    }
}
