import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {BASE_URI} from "../const";
import {Observable} from "rxjs";
import {Charge, SearchChargesRequest, CreateNewChargeRequest} from "./models";

export const     END_POINT = '/charges';

@Injectable({
  providedIn: 'root'
})
export class ChargesService {


  constructor(private http: HttpClient,
              private loginService : LoginService) { }

  public getAllCharges(): Observable<any> {
    let user  = this.loginService.user;
    let request : SearchChargesRequest = {
      userId : user.id,
      startDate : null,
      endDate : null
    }
    return this.http.post(BASE_URI+END_POINT+"/all", request );
  }

  public getAllCategories(): Observable<any> {
    return this.http.get(BASE_URI+END_POINT+"/categories");
  }

  public createNewCharge(request :CreateNewChargeRequest): Observable<any>{
    return this.http.post(BASE_URI+END_POINT+"/save", request );
  }

  public delete(chargeId : number):Observable<any>{
    return this.http.delete(BASE_URI+END_POINT+"/delete?chargeId="+chargeId);
  }

  public search(request :SearchChargesRequest): Observable<any>{
    return this.http.post(BASE_URI+END_POINT+"/search", request );
  }
}
