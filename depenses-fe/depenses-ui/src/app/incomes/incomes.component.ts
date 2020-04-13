import { Component, OnInit,ViewChild } from '@angular/core';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import { IncomesService } from "./incomes.service";
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {DATE_FORMAT} from "../const";

@Component({
  selector: 'app-incomes',
  templateUrl: './incomes.component.html',
  styleUrls: ['./incomes.component.css']
})
export class IncomesComponent implements OnInit {

  findIncomesRequest : FindIncomesRequest = new FindIncomesRequest();
  incomes : any[];
  displayedColumns: string[] = ['label','date','amount' ];
  dataSource :MatTableDataSource<any>;
  dateFormat = DATE_FORMAT;

  createNewIncomeRequest : any;

  @ViewChild(MatPaginator, {static: true})
  paginator: MatPaginator;

    @ViewChild(MatSort, {static: true})
    sort: MatSort;

  constructor(private loginService : LoginService,
              private router : Router,
              private incomesService: IncomesService) { }

  ngOnInit(): void {
    if(! this.loginService.user){
      this.router.navigateByUrl('/');
      return;
    }
    this.findIncomesRequest.user = this.loginService.user.id;

  }

  initDataSource(data : any){
    this.dataSource = new MatTableDataSource<any>(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.sortingDataAccessor = (item, property): string | number => {
    switch (property) {
        case 'date': return new Date(item.effectDate);
        default: return item[property];
    }
};
  }

  onSearch(){
      this.incomesService.findIncomesByUserAndDate(this.findIncomesRequest).subscribe(res => {
          this.initDataSource(res);
      })
  }
  onNewIncome(){
    this.createNewIncomeRequest = new Object();
    this.createNewIncomeRequest.userId = this.loginService.user.id;
  }

  onSave(){
    this.incomesService.createNewIncome(this.createNewIncomeRequest).subscribe( res => {
      this.createNewIncomeRequest = null;
    }
    )
  }

  onCancel(){
    this.createNewIncomeRequest = null;
  }

}

export class FindIncomesRequest {
  user : any;
  from : any;
  to :any;
}
