package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.List;

public interface NoteService {

  Note saveNote(Note note);

  List<Note> getNotes(int patientId);

  boolean noteExist(String id);

  void updateNote(String id, Note note);

  Note deleteNote(String id);

}
