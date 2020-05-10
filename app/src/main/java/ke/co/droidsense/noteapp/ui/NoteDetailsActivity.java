package ke.co.droidsense.noteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ke.co.droidsense.noteapp.R;

public class NoteDetailsActivity extends AppCompatActivity {
    //Member Variables...
    private Intent intent;
    private String note_title;
    private String note_desc;
    private TextView noteTitle;
    private TextView noteDesc;

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

        //Set TextView text...
        noteTitle.setText( note_title );
        noteDesc.setText( note_desc );
    }

}
