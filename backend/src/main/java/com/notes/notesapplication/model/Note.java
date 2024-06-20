package com.notes.notesapplication.model;

import com.notes.notesapplication.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Note(String title, String text, Status status) {
        this.title = title;
        this.text = text;
        this.status = status;
    }

    public Note() {
    }
}
