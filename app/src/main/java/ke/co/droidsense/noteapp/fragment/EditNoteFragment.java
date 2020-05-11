package ke.co.droidsense.noteapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;

import ke.co.droidsense.noteapp.R;
import ke.co.droidsense.noteapp.viewModel.NoteViewModel;

public class EditNoteFragment extends Fragment implements View.OnClickListener {
    //Member Variables...
    private NoteViewModel noteViewModel;
    private TextInputLayout note_title;
    private TextInputLayout note_desc;
    private Button cancel_edit_button;
    private Button edit_note_button;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Create View.
        View view = inflater.inflate( R.layout.edit_note_frag_layout, container, false );

        //Get Views...
        note_title = view.findViewById( R.id.note_title );
        note_desc = view.findViewById( R.id.note_desc );
        cancel_edit_button = view.findViewById( R.id.cancel_edit_button );
        edit_note_button = view.findViewById( R.id.edit_note_button );

        //Create anonymous click handlers.
        cancel_edit_button.setOnClickListener( this );
        edit_note_button.setOnClickListener( this );

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        //Get Fragment Manager...
        fragmentManager = getParentFragmentManager();

        //Get ViewModel.
//        sharedViewModel = ViewModelProviders.of(getActivity()).get( SharedViewModel.class );
        noteViewModel = ViewModelProviders.of( getActivity() ).get( NoteViewModel.class );
        noteViewModel.getNoteTitleMutableLiveData().observe( getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                note_title.getEditText().setText( charSequence );
            }
        } );
        noteViewModel.getNoteDescMutableLiveData().observe( getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                note_desc.getEditText().setText( charSequence );
            }
        } );
    }

    @Override
    public void onClick(View view) {
        //Use Switch statement...
        switch (view.getId()) {

            //Case 1:
            case R.id.edit_note_button:
                //Handle updating of the note item...
                noteViewModel.editNoteTitle( note_title.getEditText().getText() );
                noteViewModel.editNoteDesc( note_desc.getEditText().getText() );

                //Remove input from editText...
                note_desc.getEditText().setText( "" );
                note_title.getEditText().setText( "" );

                //Remove Fragment from view
                fragmentManager.beginTransaction().remove( this ).commit();
                break;

            //Case 2:
            case R.id.cancel_edit_button:
                //Handle cancellation by removing fragment view.
                fragmentManager.beginTransaction().remove( this ).commit();
                break;
        }
    }
}
