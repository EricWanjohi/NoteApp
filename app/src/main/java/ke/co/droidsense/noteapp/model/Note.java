package ke.co.droidsense.noteapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note")
public class Note {

    //Member Variables

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    //Constructor...

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Getters..

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Setters...

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
