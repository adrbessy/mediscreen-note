package com.mediscreen.controllers;

import com.mediscreen.exceptions.NonexistentException;
import com.mediscreen.model.Note;
import com.mediscreen.service.NoteService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    noteService.doesPatientExist(note.getPatientId());
    noteService.filledNote(note.getNote());
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
  @CrossOrigin
  @GetMapping("/notes")
  public List<Note> getNotes(@RequestParam int patientId) {
    logger.info("Get request with the endpoint 'notes'");
    noteService.doesPatientExist(patientId);
    List<Note> noteList = noteService.getNotes(patientId);
    logger.info(
        "response following the GET on the endpoint 'notes'.");
    return noteList;
  }

  /**
   * Read - Get a particular note
   * 
   * @param id The id of a note
   * @return - A note
   */
  @CrossOrigin
  @GetMapping("/note")
  public Note getNote(@RequestParam String id) {
    logger.info("Get request with the endpoint 'note'");
    noteService.noteExist(id);
    Note note = noteService.getNote(id);
    logger.info(
        "response following the GET on the endpoint 'note'.");
    return note;
  }

  /**
   * Update an existing note from a given id
   * 
   * @param id   An id
   * @param note A note object with modifications
   * @return The updated note object
   */
  @CrossOrigin
  @PutMapping("/note/{id}")
  public boolean updateNote(@PathVariable("id") final String id,
      @RequestBody Note note) {
    logger.info(
        "Put request of the endpoint 'note' with the id : {" + id + "}");
    noteService.noteExist(id);
    noteService.updateNote(id, note);
    logger.info(
        "response following the PUT on the endpoint 'note'.");
    return true;
  }

  /**
   * Delete - Delete a note
   * 
   * @param id An id
   * @return - The deleted note
   */
  @CrossOrigin
  @DeleteMapping("/note")
  public Note deleteNote(@RequestParam String id) {
    Note note = null;
    boolean existingNote = false;
    logger.info("Delete request with the endpoint 'note'");
    existingNote = noteService.noteExist(id);
    if (existingNote) {
      note = noteService.deleteNote(id);
      logger.info(
          "response following the DELETE on the endpoint 'note'.");
    }
    if (!existingNote) {
      logger.error("The note with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The note with the id " + id + " doesn't exist.");
    }
    return note;
  }

}
