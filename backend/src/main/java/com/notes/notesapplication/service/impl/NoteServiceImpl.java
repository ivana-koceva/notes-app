package com.notes.notesapplication.service.impl;

import com.notes.notesapplication.model.Note;
import com.notes.notesapplication.model.dto.NoteDTO;
import com.notes.notesapplication.model.enums.Status;
import com.notes.notesapplication.model.exceptions.NoteNotFoundException;
import com.notes.notesapplication.repository.NotesRepository;
import com.notes.notesapplication.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NotesRepository notesRepository;

    public NoteServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Note> findAll() {
        return this.notesRepository.findAll();
    }

    @Override
    public Optional<Note> findById(Long id) {
        Note n = this.notesRepository.findById(id).orElseThrow(() -> new NoteNotFoundException());
        return Optional.of(n);
    }

    @Override
    public Optional<Note> save(String title, String text, Status status) {
        Note n = new Note(title, text, status);
        this.notesRepository.save(n);
        return Optional.of(n);
    }

    @Override
    public Optional<Note> save(NoteDTO noteDTO) {
        Note n = new Note(noteDTO.getTitle(), noteDTO.getText(), noteDTO.getStatus());
        this.notesRepository.save(n);
        return Optional.of(n);
    }

    @Override
    public Optional<Note> update(Long id, String title, String text, Status status) {
        Note n = this.notesRepository.findById(id).orElseThrow(() -> new NoteNotFoundException());
        n.setTitle(title);
        n.setText(text);
        n.setStatus(status);
        this.notesRepository.save(n);
        return Optional.of(n);
    }

    @Override
    public Optional<Note> update(Long id, NoteDTO noteDTO) {
        Note n = this.notesRepository.findById(id).orElseThrow(() -> new NoteNotFoundException());
        n.setTitle(noteDTO.getTitle());
        n.setText(noteDTO.getText());
        n.setStatus(noteDTO.getStatus());
        this.notesRepository.save(n);
        return Optional.of(n);
    }

    @Override
    public Optional<Note> findByTitle(String title) {
        return this.notesRepository.findByTitle(title);
    }

    @Override
    public void deleteById(Long id) {
        try {
            notesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete note with ID: " + id, e);
        }
    }
}
