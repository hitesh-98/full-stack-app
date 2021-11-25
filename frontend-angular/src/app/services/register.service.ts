import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  url = 'http://localhost:4300';

  constructor(private http: HttpClient) { }

  registerUser(credentials: any) {
    return this.http.post(`${this.url}/register`, credentials);
  }
}
