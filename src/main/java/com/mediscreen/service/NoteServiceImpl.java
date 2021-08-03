package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.repositories.NoteRepository;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    TimeZone zone = TimeZone.getDefault();
    System.out.println(zone.getDisplayName());
    System.out.println(zone.getID());
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

}
