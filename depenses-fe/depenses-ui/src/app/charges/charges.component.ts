import {Component, OnInit, ViewChild} from '@angular/core';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {Charge, ChargeType} from "./models";
import {ChargesService} from "./charges.service";
import {MatSort} from "@angular/material/sort";
import {DATE_FORMAT} from "../const";

@Component({
  selector: 'app-charges',
  templateUrl: './charges.component.html',
  styleUrls: ['./charges.component.css']
})
export class ChargesComponent implements OnInit {

  @ViewChild('periodicSort', {static: false}) periodicSort: MatSort;
  @ViewChild('oneTimeSort', {static: false}) oneTimeSort: MatSort;

  dateFormat = DATE_FORMAT;

  periodicChargesDisplayedColumns: string[] = ['label', 'amount', 'category', 'period', 'startDate', 'endDate','active'];
  oneTimeChargesDisplayedColumns: string[] = ['label', 'amount', 'category', 'effectDate'];

  periodicChargesDatasource : MatTableDataSource<Charge> ;
  onetimeChargesDatasource : MatTableDataSource<Charge> ;
  private periodicChares : Charge[];
  private oneTimeCharges : Charge[];

  constructor(private loginService : LoginService,
              private chargesService: ChargesService,
              private router : Router) { }

  ngOnInit(): void {
    if(! this.loginService.user){
      this.router.navigateByUrl('/');
      return;
    }

    this.initData();
  }

  private initData(){
     this.chargesService.getAllCharges().subscribe(response => {
       this.periodicChares = response.filter(c => c.type == ChargeType.PERIODIC);
       this.oneTimeCharges = response.filter(c => c.type == ChargeType.ONE_TIME);

       this.periodicChargesDatasource = new MatTableDataSource(this.periodicChares);
       this.periodicChargesDatasource.sortingDataAccessor = (item, property) => {
         switch(property) {
           case 'category': return item.category.code;
           default: return item[property];
         }
       };
       this.periodicChargesDatasource.sort = this.periodicSort;


       this.onetimeChargesDatasource = new MatTableDataSource<Charge>(this.oneTimeCharges);
       this.onetimeChargesDatasource.sortingDataAccessor = (item, property) => {
         switch(property) {
           case 'category': return item.category.code;
           default: return item[property];
         }
       };
       this.onetimeChargesDatasource.sort = this.oneTimeSort;
     });
  }

}
