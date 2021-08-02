package com.mediscreen.service;

import com.mediscreen.repositories.NoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

  private static final Logger logger = LogManager.getLogger(NoteServiceImpl.class);

  @Autowired
  private NoteRepository noteRepository;

}
