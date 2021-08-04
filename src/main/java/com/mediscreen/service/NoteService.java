package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.List;

public interface NoteService {

  Note saveNote(Note note);

  List<Note> getNotes(int patientId);

  boolean noteExist(String id);

  void updateNote(String id, Note note);

  Note deleteNote(String id);

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  boolean doesPatientExist(Integer patientId);

  /**
   * Check if a note is empty or null
   * 
   * @param note The content of a note
   * @return true if the note is not null or empty
   */
  boolean filledNote(String note);

}
