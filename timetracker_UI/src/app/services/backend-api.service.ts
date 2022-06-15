import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  checkIn = "api/v1/timesheet/shifts/start";
  checkOut = "api/v1/timesheet/shifts/end";
  checkView = "api/v1/timesheet/shifts";
  baseURL: string;
  constructor(private http: HttpClient) {
    this.baseURL = "http://localhost:8080/";
   }

  public getHeaders(usersName: string): HttpHeaders{
    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
    .set('user-name', usersName);
    return headers;
  };
  getCheckIn(usersName: string){
    return this.http.get(this.baseURL+this.checkIn, {headers: this.getHeaders(usersName)}).pipe(
      catchError(error => throwError(error))

    ).toPromise();
  }
  getCheckOut(usersName: string){
    return this.http.get(this.baseURL+this.checkOut, {headers: this.getHeaders(usersName)}).pipe(
      catchError(error => throwError(error))

    ).toPromise();
  }
  getView(usersName: string){
    return this.http.get(this.baseURL+this.checkView, {headers: this.getHeaders(usersName)}).pipe(
      catchError(error => throwError(error))

    ).toPromise();
  }
}
