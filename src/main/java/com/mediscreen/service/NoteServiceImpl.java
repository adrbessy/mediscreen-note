package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.repositories.NoteRepository;
import java.time.LocalDate;
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
    note.setDate(LocalDate.now());
    Note savedNote = noteRepository.insert(note);
    return savedNote;
  }

}
