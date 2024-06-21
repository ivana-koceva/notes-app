import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Notes} from "./notes";
import {NotesService} from "./notes.service";
import {response} from "express";
import {HttpErrorResponse} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {FormsModule, NgForm} from "@angular/forms";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  public notes: Notes[] = [];
  public editNote: Notes | null = null;
  public deleteNote: Notes | null = null;

  constructor(private notesService: NotesService) {}

  public getNotes(): void {
    this.notesService.getNotes().subscribe(
      (response: Notes[]) => {
        this.notes = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  ngOnInit(): void {
    this.getNotes();
  }

  public onAddNote(addForm: NgForm): void {
    document.getElementById("add-note-form")?.click();
    this.notesService.addNotes(addForm.value).subscribe(
      (response: Notes) => {
        console.log(response);
        this.getNotes();
        addForm.reset();
      },
    (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
    }
    )
  }

  public searchNote(key: string): void {
    const results: Notes[] = [];
    for(const note of this.notes) {
      if(note.title.toLowerCase().indexOf(key.toLowerCase())!=-1) {
        results.push(note);
      }
    }
    this.notes = results;
    if(results.length === 0 || !key) {
      this.getNotes();
    }
  }
  public onUpdateNote(note: Notes): void {
    this.notesService.updateNotes(note.id, note).subscribe(
      (response: Notes) => {
        console.log(response);
        this.getNotes();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onDeleteNote(noteId: number): void {
    this.notesService.deleteNotes(noteId).subscribe(
      (response: void) => {
        this.getNotes();
      },
      (error: HttpErrorResponse) => {
      }
    )
  }

  public onOpenModal(note: Notes | null, mode: string): void {
    const container = document.getElementById("container");
    const button = document.createElement("button");
    button.type = "button";
    button.style.display="none";
    button.setAttribute("data-toggle", "modal");
    if(mode === "add") {
        button.setAttribute("data-target", "#addNoteModal");
    }
    if(mode === "edit") {
      this.editNote = note;
      button.setAttribute("data-target", "#updateNoteModal");
    }
    if(mode === "delete") {
      this.deleteNote = note;
      button.setAttribute("data-target", "#deleteNoteModal");
    }
    container?.appendChild(button);
    button.click();
  }
}
