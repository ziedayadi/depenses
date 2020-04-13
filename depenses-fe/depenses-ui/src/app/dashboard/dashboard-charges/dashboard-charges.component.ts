import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'dashboard-charges',
  templateUrl: './dashboard-charges.component.html',
  styleUrls: ['./dashboard-charges.component.css']
})
export class DashboardChargesComponent implements OnInit {

  @Input('oneTimeCharge')
  public oneTimeCharge : any[];

  @Input('periodicCharges')
  public periodicCharges : any[];

  constructor() { }

  ngOnInit(): void {
  }

  getSumPeriodic() : number{
    let sum =  this.periodicCharges.map(ch => ch.amount).reduce((a,b) => a+b , 0);
    return sum;
  }

  getSumOneTime() : number{
    let sum =  this.oneTimeCharge.map(ch => ch.amount).reduce((a,b) => a+b , 0);
    return sum;
  }

}
