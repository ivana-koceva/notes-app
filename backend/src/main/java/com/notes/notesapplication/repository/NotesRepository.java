package com.notes.notesapplication.repository;

import com.notes.notesapplication.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByTitle(String title);
}
