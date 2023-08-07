import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IStore } from './interfaces/IStore';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.apiUrl

  getBooksDividedByYear() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    })

    return this.http.get<IStore[]>(`${this.baseUrl}/account/stores`, { headers })
  }
}
