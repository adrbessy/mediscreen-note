package com.mediscreen.controllers;

import com.mediscreen.service.NoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteRestController {

  private static final Logger logger = LogManager.getLogger(NoteRestController.class);

  @Autowired
  private NoteService noteService;

}
