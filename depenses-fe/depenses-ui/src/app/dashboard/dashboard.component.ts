import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import {ChargesService} from "../charges/charges.service";
import {SearchChargesRequest,Charge,ChargeType,ChargeCategory} from "../charges/models";
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private loginService : LoginService,
              private chargesService: ChargesService,
              private router : Router,
              public dialog: MatDialog) { }


  periodicCharges : Charge[];
  oneTimeCharge : Charge[];
  checkedCategories : CheckedCategory[];
  fromDate : Date;
  toDate : Date;

  ngOnInit(): void {
   if(! this.loginService.user){
     this.router.navigateByUrl('/');
   }
   this.chargesService.getAllCategories().subscribe(response => {
     let dtos = response;
     this.checkedCategories = dtos.map(dto => {
       let checkedCat : CheckedCategory = new CheckedCategory();
       checkedCat.checked=true;
       checkedCat.categoryCode=dto.code;
       checkedCat.categoryLabel=dto.label;
       return checkedCat;
     })

   });

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
      }, err => this.openErrorDialog(err) )
    }

   getSumPeriodic() : number{
     let sum =  this.getCheckedPeriodicCharges().map(ch => ch.amount).reduce((a,b) => a+b , 0);
     return sum;
   }

   getSumOneTime() : number{
     let sum =  this.oneTimeCharge.map(ch => ch.amount).reduce((a,b) => a+b , 0);
     return sum;
   }

   getAllCategoriesAreChecked() : boolean {
     return this.checkedCategories.map(c => c.checked).reduce((a,b)=> a && b );
   }

   onAllCategoriesChecked($event){
     this.checkedCategories.forEach(element => {
        element.checked = $event.checked
     });
   }
   openErrorDialog(error : any): void {
    const dialogRef = this.dialog.open(ErrorDialogComponent, {
      data: {error : error}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

   isCategoryCheched(cat : ChargeCategory){
     return this.checkedCategories.filter(c => c.checked).map(c => c.categoryCode).indexOf(cat.code) > -1;
   }

   public getCheckedPeriodicCharges(){
     return this.periodicCharges.filter(ch => this.isCategoryCheched(ch.category));
   }

}

export class CheckedCategory {
  checked : boolean ;
  categoryCode: String;
  categoryLabel : String;
}
