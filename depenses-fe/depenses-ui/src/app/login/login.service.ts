import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BASE_URI} from "../const";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  user : any;
  constructor(private http: HttpClient) { }

  public login(loginRequest : LoginRequest): Observable<any>{
    return this.http.post(BASE_URI+"/users/login", loginRequest);
  }
}


export class LoginRequest {
  login : String;
  password : String
}
