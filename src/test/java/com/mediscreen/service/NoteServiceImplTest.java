package com.mediscreen.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import com.mediscreen.model.Note;
import com.mediscreen.proxies.MicroservicePatientProxy;
import com.mediscreen.repositories.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class NoteServiceImplTest {

  @Autowired
  private NoteService noteService;

  @MockBean
  private NoteRepository noteRepositoryMock;

  @MockBean
  private MicroservicePatientProxy microservicePatientProxy;

  @Test
  public void testDoesPatientExist() {
    Integer patientId = 1;

    when(microservicePatientProxy.doesPatientExist(patientId)).thenReturn(true);

    boolean result = noteService.doesPatientExist(patientId);
    assertTrue(result);
  }

  @Test
  public void testSaveNote() {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");

    when(noteRepositoryMock.insert(note)).thenReturn(note);

    Note result = noteService.saveNote(note);
    assertThat(result).isEqualTo(note);
  }

  @Test
  public void testGetNotes() {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");
    List<Note> noteList = new ArrayList<>();
    noteList.add(note);

    when(noteRepositoryMock.findByPatientId(note.getPatientId())).thenReturn(noteList);

    List<Note> result = noteService.getNotes(note.getPatientId());
    assertThat(result).isEqualTo(noteList);
  }

  @Test
  public void testNoteExist() {
    String id = "C2I";

    when(noteRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = noteService.noteExist(id);
    assertTrue(result);
  }

  @Test
  public void testFilledNote() {
    String note = "He feels very bad.";
    boolean result = noteService.filledNote(note);
    assertTrue(result);
  }

  @Test
  public void testUpdateNote() {
    Optional<Note> note = Optional.ofNullable(new Note());
    note.get().setPatientId(1);
    note.get().setNote("Plant diet is necessary!");
    Note note2 = new Note();
    note2.setPatientId(1);
    note2.setNote("Plant diet is recommended!");

    when(noteRepositoryMock.findById(note.get().getId())).thenReturn(note);
    when(noteRepositoryMock.save(note.get())).thenReturn(note.get());

    noteService.updateNote(note.get().getId(), note2);
    assertThat(note.get().getNote()).isEqualTo(note2.getNote());
  }

}
