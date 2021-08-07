package com.mediscreen.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.model.Note;
import com.mediscreen.service.NoteService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NoteService noteServiceMock;

  @Test
  public void testGetNotes() throws Exception {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");
    List<Note> noteList = new ArrayList<>();
    noteList.add(note);

    when(noteServiceMock.getNotes(1)).thenReturn(noteList);
    when(noteServiceMock.doesPatientExist(1)).thenReturn(true);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/notes?patientId=1"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetNote() throws Exception {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");

    when(noteServiceMock.noteExist(note.getId())).thenReturn(true);
    when(noteServiceMock.getNote(note.getId())).thenReturn(note);


    mockMvc
        .perform(MockMvcRequestBuilders.get("/note?id=" + note.getId()))
        .andExpect(status().isOk());
  }

  @Test
  public void testCreateNote() throws Exception {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");

    when(noteServiceMock.doesPatientExist(1)).thenReturn(true);
    when(noteServiceMock.filledNote(note.getNote())).thenReturn(true);
    when(noteServiceMock.saveNote(note)).thenReturn(note);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/note")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(note));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testUpdateNote() throws Exception {
    Note note = new Note();
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");

    when(noteServiceMock.noteExist("C2I")).thenReturn(true);
    doNothing().when(noteServiceMock).updateNote("C2I", note);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/note/C2I")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(note));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testDeleteNote() throws Exception {
    Note note = new Note();
    note.setId("C2I");
    note.setPatientId(1);
    note.setNote("Plant diet is necessary!");

    when(noteServiceMock.noteExist(note.getId())).thenReturn(true);
    when(noteServiceMock.deleteNote(note.getId())).thenReturn(note);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/note?id=C2I"))
        .andExpect(status().isOk());
  }

}
