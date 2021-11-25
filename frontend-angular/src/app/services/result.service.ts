import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  url = 'http://localhost:4300';

  constructor(private http: HttpClient) { }

  getResults() {
    return this.http.get(`${this.url}/api/getResults`).pipe(map((res:any) => {
      return res;
    }))
  }
}
