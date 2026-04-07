import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ApiResponse, Producto } from '../interfaces/producto.interface';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private http = inject(HttpClient);
  private url = 'http://localhost:8090/api/producto';

  getAll(): Observable<Producto[]> {
    return this.http.get<ApiResponse<Producto[]>>(this.url).pipe(
      map(res => res.data)
    );
  }

  getById(id: number): Observable<Producto> {
    return this.http.get<ApiResponse<Producto>>(`${this.url}/${id}`).pipe(
      map(res => res.data)
    );
  }

  create(producto: Producto): Observable<Producto> {
    return this.http.post<ApiResponse<Producto>>(this.url, producto).pipe(
      map(res => res.data)
    );
  }

  update(id: number, producto: Producto): Observable<Producto> {
    return this.http.put<ApiResponse<Producto>>(`${this.url}/${id}`, producto).pipe(
      map(res => res.data)
    );
  }

  delete(id: number): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.url}/${id}`).pipe(
      map(() => void 0)
    );
  }
}
