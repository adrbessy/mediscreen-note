package com.mediscreen.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "note")
@Entity
public class Note {

  @Id
  private String id;

  @NotBlank(message = "PatientId is mandatory")
  private int patientId;

  private Date date;

  @NotBlank(message = "Note is mandatory")
  private String note;

}
