import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private http = inject(HttpClient);
  private url: string = 'http://localhost:8090/api/auth/login';
  constructor() { }


  public login(username: string, password: string): Observable<any> {
    return this.http.post(this.url, { username, password });
  }

  public isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  public logout(): void {
    localStorage.removeItem('token');
  }

  public getRolId(): number | null {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.rolId ?? null;
    } catch {
      return null;
    }
  }

  public isAdmin(): boolean {
    return this.getRolId() === 1;
  }

}
