package ke.co.droidsense.noteapp.model;

import androidx.room.Entity;

@Entity
public class Note {
    //Member Variables

    private String title;
    private String description;

    //Constructor...

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Getters..

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Setters...

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
