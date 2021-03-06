package com.mediscreen.service;

import com.mediscreen.exceptions.IsForbiddenException;
import com.mediscreen.exceptions.NonexistentException;
import com.mediscreen.model.Note;
import com.mediscreen.proxies.MicroservicePatientProxy;
import com.mediscreen.repositories.NoteRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

  @Autowired
  private MicroservicePatientProxy microservicePatientProxy;

  /**
   * Get a note
   * 
   * @param id A note id
   * @return the note
   */
  @Override
  public Note getNote(String id) {
    logger.debug("in the method getNote in the class NoteServiceImpl");
    Optional<Note> note = noteRepository.findById(id);
    return note.get();
  }

  /**
   * Check if the patient id exists
   * 
   * @param patientId patient id
   * @return true if it exists
   */
  @Override
  public boolean doesPatientExist(Integer patientId) {
    logger.debug("in the method doesPatientExist in the class NoteServiceImpl");
    boolean patientExists = microservicePatientProxy.doesPatientExist(patientId);
    return patientExists;
  }

  /**
   * Save a note
   * 
   * @param note A note to save
   * @return the saved note
   */
  @Override
  public Note saveNote(Note note) {
    logger.debug("in the method saveNote in the class NoteServiceImpl");
    note.setDate(LocalDateTime.now(ZoneId.of("Europe/Paris")));
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
    if (!noteExist) {
      logger.error("The note with the id " + id + " doesn't exist.");
      throw new NonexistentException("The note with the id " + id + " doesn't exist.");
    }
    return noteExist;
  }

  /**
   * Check if a note variable is empty or null
   * 
   * @param note The content of a note
   * @return true if the note is not null or empty
   */
  @Override
  public boolean filledNote(String note) {
    logger.debug("in the method filledNote in the class NoteServiceImpl");
    if (note == null || note.length() == 0) {
      logger.error("The note content is empty or null.");
      throw new IsForbiddenException("The note content is empty or null.");
    } else {
      return true;
    }
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
    boolean filledNote = filledNote(note.getNote());
    Optional<Note> noteToUpdate = noteRepository.findById(id);
    if (filledNote) {
      if (noteToUpdate.isPresent()) {
        noteToUpdate.get().setNote(note.getNote());
      }
    }
    noteRepository.save(noteToUpdate.get());
  }

  /**
   * Delete a note
   * 
   * @param id An id
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
