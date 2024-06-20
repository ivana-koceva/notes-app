package com.notes.notesapplication.model.dto;

import com.notes.notesapplication.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class NoteDTO {

    private String title;
    private String text;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public NoteDTO(String title, String text, Status status) {
        this.title = title;
        this.text = text;
        this.status = status;
    }
}
