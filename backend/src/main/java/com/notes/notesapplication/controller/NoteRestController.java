package com.notes.notesapplication.controller;

import com.notes.notesapplication.model.Note;
import com.notes.notesapplication.model.dto.NoteDTO;
import com.notes.notesapplication.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
@RequestMapping("/api/notes")
public class NoteRestController {

    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> findAll() {
        return this.noteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        return this.noteService.findById(id)
                .map(note -> ResponseEntity.ok().body(note))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Note> create(@RequestBody NoteDTO noteDTO) {
        return this.noteService.save(noteDTO)
                .map(note -> ResponseEntity.ok().body(note))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        return this.noteService.update(id, noteDTO)
                .map(note -> ResponseEntity.ok().body(note))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.noteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
