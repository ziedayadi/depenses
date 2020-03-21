import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import {ChargesService} from "../charges/charges.service";
import {SearchChargesRequest,Charge,ChargeType } from "../charges/models";



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private loginService : LoginService,
              private chargesService: ChargesService,
              private router : Router) { }


  periodicCharges : Charge[];
  oneTimeCharge : Charge[];
  fromDate : Date;
  toDate : Date;

  ngOnInit(): void {
   if(! this.loginService.user){
     this.router.navigateByUrl('/');
   }


  }

  onSearch(){
      let searchRequest : SearchChargesRequest = {
        userId : this.loginService.user.id,
        startDate :this.fromDate,
        endDate : this.toDate
      }

      this.chargesService.search(searchRequest).subscribe(response => {
        this.periodicCharges = response.filter(c => c.type == ChargeType.PERIODIC);
        this.oneTimeCharge = response.filter(c => c.type == ChargeType.ONE_TIME);
      });
  }

}
