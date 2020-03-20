import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import {ChargesService} from "../charges/charges.service";
import {SearchChargesRequest,Charge } from "../charges/models";



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private loginService : LoginService,
              private chargesService: ChargesService,
              private router : Router) { }


  charges : Charge[];

  ngOnInit(): void {
   if(! this.loginService.user){
     this.router.navigateByUrl('/');
   }

   // get first date and last date
   let currentDate = new Date();
   let startDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
   let endDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);

   let searchRequest : SearchChargesRequest = {
     userId : this.loginService.user.id,
     startDate :startDate,
     endDate : endDate
   }

   this.chargesService.search(searchRequest).subscribe(response => {
     this.charges = response;
   })
  }

}
