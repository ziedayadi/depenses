import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {BASE_URI} from "../const";
import {Observable} from "rxjs";
import {Charge, SearchChargesRequest} from "./models";

export const     END_POINT = '/charges';

@Injectable({
  providedIn: 'root'
})
export class ChargesService {


  constructor(private http: HttpClient,
              private loginService : LoginService) { }

  getAllCharges(): Observable<any> {
    let user  = this.loginService.user;
    let request : SearchChargesRequest = {
      userId : user.id
    }
    return this.http.post(BASE_URI+END_POINT+"/all", request );
  }
}
