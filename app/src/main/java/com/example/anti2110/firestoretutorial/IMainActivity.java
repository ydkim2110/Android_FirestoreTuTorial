package com.example.anti2110.firestoretutorial;

import com.example.anti2110.firestoretutorial.Models.Note;

/**
 * Created by anti2110 on 2018-11-17
 */
public interface IMainActivity {

    void createNewNote(String title, String content);
    void onNoteSelected(Note note);
    void updateNote(Note note);
    void deleteNote(Note note);

}
