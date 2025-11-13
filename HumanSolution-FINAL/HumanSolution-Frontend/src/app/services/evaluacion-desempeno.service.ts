import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface EvaluacionDesempeno {
  id?: string;
  usuario: { id: string };
  fecha: string;
  calificacion: number;
  observacion: string;
  criterios?: string;
}

@Injectable({
  providedIn: 'root'
})
export class EvaluacionDesempenoService {
  private apiUrl = 'http://localhost:8080/api/v1/evaluaciones-desempeno';

  constructor(private http: HttpClient) {}

  create(evaluacion: EvaluacionDesempeno): Observable<any> {
    return this.http.post(this.apiUrl, evaluacion);
  }

  list(): Observable<EvaluacionDesempeno[]> {
    return this.http.get<EvaluacionDesempeno[]>(this.apiUrl);
  }

  findById(id: string): Observable<EvaluacionDesempeno> {
    return this.http.get<EvaluacionDesempeno>(`${this.apiUrl}/${id}`);
  }

  findByUsuario(idUsuario: string): Observable<EvaluacionDesempeno[]> {
    return this.http.get<EvaluacionDesempeno[]>(`${this.apiUrl}/usuario/${idUsuario}`);
  }

  update(id: string, evaluacion: EvaluacionDesempeno): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, evaluacion);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
