import { Component, OnInit, Input } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-dashboard-charts',
  templateUrl: './dashboard-charts.component.html',
  styleUrls: ['./dashboard-charts.component.css']
})
export class DashboardChartsComponent implements OnInit {

  @Input()
  public catChartData ;

  @Input()
  public allCharges ;

  public categoryLabels : any[];
  public categoryAmounts : any[];

  public chargesByDayLabels : any[];
  public chargesByDayData : any[];

  public catgeChartType: string = 'pie';
  public chargeByDayChartType: string = 'line';


  constructor(public datepipe: DatePipe) {

   }

  ngOnInit(): void {
    this.initCategoriesLabels();
    this.initCategoriesAmounts();
    this.sortAllCharges();
    this.initChargeByDateLabels();
    this.initChargeByDateData();
  }

  sortAllCharges(){
    this.allCharges.sort((a, b) => a.debitDate < b.debitDate ? -1 : a.debitDate > b.debitDate ? 1 : 0)
  }

  initCategoriesLabels(){
    this.categoryLabels = this.catChartData.map(c => c.catLabel);
  }

  initCategoriesAmounts(){
    let data = this.catChartData.map(c => c.catAmount);
    this.categoryAmounts = [
    { data: data, label: 'Charges by category' }
  ];
  }

  initChargeByDateLabels(){
    this.chargesByDayLabels = this.allCharges.map(c =>  this.datepipe.transform(c.debitDate, 'dd/MM/yyyy'))
  }

  initChargeByDateData(){
    let data = this.allCharges.map(c => c.amount);
    this.chargesByDayData = [
    { data: data, label: 'Charges per day' }
  ];
  }

  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

  public chartColors: Array<any> = [
   {
     backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
     hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774'],
     borderWidth: 2,
   }
 ];

  public chartOptions: any = {
    responsive: true
  };

}
