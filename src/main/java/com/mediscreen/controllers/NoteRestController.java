package com.mediscreen.controllers;

import com.mediscreen.model.Note;
import com.mediscreen.service.NoteService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteRestController {

  private static final Logger logger = LogManager.getLogger(NoteRestController.class);

  @Autowired
  private NoteService noteService;

  /**
   * Add a new note
   * 
   * @param note An object note
   * @return true
   */
  @CrossOrigin
  @PostMapping("/note")
  public boolean createNote(@RequestBody Note note) {
    logger.info("Post request with the endpoint 'note'");
    noteService.saveNote(note);
    logger.info(
        "response following the Post on the endpoint 'note' with the given note : {"
            + note.toString() + "}");
    return true;
  }

  /**
   * Read - Get all the notes of one user
   * 
   * @param patientId The id of a patient
   * @return - A List of notes
   */
  @GetMapping("/notes")
  public List<Note> getNotes(@RequestParam int patientId) {
    logger.info("Get request with the endpoint 'notes'");
    List<Note> noteList = noteService.getNotes(patientId);
    logger.info(
        "response following the GET on the endpoint 'notes'.");
    return noteList;
  }

}
