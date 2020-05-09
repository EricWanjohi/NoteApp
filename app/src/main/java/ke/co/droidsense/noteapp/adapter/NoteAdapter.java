package ke.co.droidsense.noteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.droidsense.noteapp.R;
import ke.co.droidsense.noteapp.model.Note;
import ke.co.droidsense.noteapp.ui.NoteDetailsActivity;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    //Member Variables...
    private List<Note> noteList;
    private Context context;

    //Constructor...
    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View item...
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.note_item, parent, false );

        return new NoteViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        //Create object item...
        Note note = noteList.get( position );

        //Bind view with data.
        holder.note_title.setText( note.getTitle() );
        holder.note_desc.setText( note.getDescription() );

        //Set tag on object item...
        holder.itemView.setTag( note );
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    //Note ViewHolder...
    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Member Variables...
        private TextView note_title, note_desc;

        //Constructor...
        public NoteViewHolder(@NonNull View itemView) {
            super( itemView );

            //Initializations...
            note_title = itemView.findViewById( R.id.note_title );
            note_desc = itemView.findViewById( R.id.note_desc );

            //Set Click Listener.
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            //Get object tag and handle click events.
            View taggedNote = (View) view.getTag();

            //Transition to details activity.
            if (view == taggedNote) {
                //Create Intent.
                Intent detailsIntent = new Intent( context, NoteDetailsActivity.class );
                detailsIntent.putExtra( "note_title", note_title.getText().toString() );
                detailsIntent.putExtra( "note_desc", note_desc.getText().toString() );
                context.startActivity( detailsIntent );
            }
        }
    }
}
