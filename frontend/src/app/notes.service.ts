import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notes} from "./notes";

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  private apiServerUrl = "http://localhost:8080/api/";

  constructor(private http: HttpClient) {}

  public getNotes(): Observable<Notes[]> {
    return this.http.get<Notes[]>(`${this.apiServerUrl}notes`);
  }

  public addNotes(note: Notes): Observable<Notes> {
    return this.http.post<Notes>(`${this.apiServerUrl}notes/add`, note);
  }

  public updateNotes(noteId: number, note: Notes): Observable<Notes> {
    return this.http.post<Notes>(`${this.apiServerUrl}notes/edit/${noteId}`, note);
  }

  public deleteNotes(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}notes/delete/${noteId}`);
  }
}
