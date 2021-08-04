package com.mediscreen.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "note")
public class Note {

  @Id
  private String id;

  @NotNull(message = "must not be null")
  private Integer patientId;

  private Date date;

  @NotBlank(message = "Note is mandatory")
  private String note;

}
