package com.notes.notesapplication.service;

import com.notes.notesapplication.model.Note;
import com.notes.notesapplication.model.dto.NoteDTO;
import com.notes.notesapplication.model.enums.Status;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    List<Note> findAll();
    Optional<Note> findById(Long id);
    Optional<Note> save(String title, String text, Status status);
    Optional<Note> save(NoteDTO noteDTO);
    Optional<Note> update(Long id, String title, String text, Status status);
    Optional<Note> update(Long id, NoteDTO noteDTO);
    Optional<Note> findByTitle(String title);
    void deleteById(Long id);
}
