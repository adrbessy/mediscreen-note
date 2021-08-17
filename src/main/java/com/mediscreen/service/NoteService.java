package com.mediscreen.service;

import com.mediscreen.model.Note;
import java.util.List;

public interface NoteService {

  /**
   * Save a note
   * 
   * @param note A note
   * @return the saved note
   */
  Note saveNote(Note note);

  /**
   * Get all the notes of a patient
   * 
   * @param patientId A patient id
   * @return the notes of the patient
   */
  List<Note> getNotes(int patientId);

  /**
   * Check if a note exist
   * 
   * @param id A note id
   * @return true if it exists
   */
  boolean noteExist(String id);

  /**
   * Update a note
   * 
   * @param id   The id of the note to update
   * @param note A new note
   */
  void updateNote(String id, Note note);

  /**
   * Delete a note
   * 
   * @param id A note id
   * @return the deleted note
   */
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

  /**
   * Get a note
   * 
   * @param id A note id
   * @return the note
   */
  Note getNote(String id);

}
