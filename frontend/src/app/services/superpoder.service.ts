import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Superpoder } from '../models/superpoder.model';

@Injectable({ providedIn: 'root' })
export class SuperpoderService {
  private apiUrl = 'http://localhost:8080/superpoderes';

  constructor(private http: HttpClient) {}

  listar(): Observable<Superpoder[]> {
    return this.http.get<Superpoder[]>(this.apiUrl);
  }

  criar(sp: Superpoder) {
    return this.http.post<Superpoder>(this.apiUrl, sp);
  }
}
