package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.repositories.NoteRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteServiceImpl implements NoteService {

  private static final Logger logger = LogManager.getLogger(NoteServiceImpl.class);

  @Autowired
  private NoteRepository noteRepository;

  /**
   * Save a note
   * 
   * @param note A note to save
   * @return the saved note
   */
  @Override
  public Note saveNote(Note note) {
    logger.debug("in the method saveNote in the class NoteServiceImpl");
    // TimeZone zone = TimeZone.getDefault();
    // System.out.println(zone.getDisplayName());
    // System.out.println(zone.getID());
    note.setDate(new Date());
    Note savedNote = noteRepository.insert(note);
    return savedNote;
  }

  /**
   * Get the notes of one patient
   * 
   * @param patientId the id of a patient
   * @return the list of his notes
   */
  @Override
  public List<Note> getNotes(int patientId) {
    logger.debug("in the method getNotes in the class NoteServiceImpl");
    List<Note> noteList = noteRepository.findByPatientId(patientId);
    return noteList;
  }

  /**
   * Check if the given note id exists.
   * 
   * @param id The note id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean noteExist(String id) {
    logger.debug("in the method noteExist in the class NoteServiceImpl");
    boolean noteExist = false;
    noteExist = noteRepository.existsById(id);
    return noteExist;
  }

  /**
   * Update a note
   * 
   * @param id   The id of the note to update
   * @param note A note to update
   */
  @Override
  public void updateNote(String id, Note note) {
    logger.debug("in the method updateNote in the class NoteServiceImpl");
    // Note noteBis = null;
    Optional<Note> noteToUpdate = noteRepository.findById(id);
    if (note.getNote() != null) {
      if (noteToUpdate.isPresent()) {
        // noteBis = noteToUpdate.get();
        noteToUpdate.get().setNote(note.getNote());
      }
    }
    noteRepository.save(noteToUpdate.get());
  }

  /**
   * Delete a note
   * 
   * @param note A note
   * @return the deleted note
   */
  @Override
  @Transactional
  public Note deleteNote(String id) {
    logger.debug("in the method deleteNote in the class NoteServiceImpl");
    Optional<Note> note = noteRepository.findById(id);
    noteRepository.deleteById(id);
    return note.get();
  }

}
