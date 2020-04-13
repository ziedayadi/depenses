import { Injectable } from '@angular/core';
import { FindIncomesRequest } from "./incomes.component";
import {HttpClient} from "@angular/common/http";
import {BASE_URI} from "../const";
import {Observable} from "rxjs";

export const     INCOMES_END_POINT = '/incomes';


@Injectable({
  providedIn: 'root'
})
export class IncomesService {

  constructor(private http: HttpClient) { }

  public findIncomesByUserAndDate(req : FindIncomesRequest): Observable<any>{
    return this.http.post(BASE_URI+INCOMES_END_POINT+"/find", req);
  }

  public createNewIncome(req : any): Observable<any>{
    return this.http.post(BASE_URI+INCOMES_END_POINT+"/new", req);
  }
}
