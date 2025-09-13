import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Heroi } from '../models/heroi.model';

@Injectable({ providedIn: 'root' })
export class HeroiService {
  private apiUrl = 'http://localhost:8080/herois';

  constructor(private http: HttpClient) {}

  listar(): Observable<Heroi[]> {
    return this.http.get<Heroi[]>(this.apiUrl);
  }

  buscarPorId(id: number) {
    return this.http.get<Heroi>(`${this.apiUrl}/${id}`);
  }

  criar(heroi: Heroi) {
    return this.http.post<Heroi>(this.apiUrl, heroi);
  }

  atualizar(id: number, heroi: Heroi) {
    return this.http.put<Heroi>(`${this.apiUrl}/${id}`, heroi);
  }

  deletar(id: number) {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
