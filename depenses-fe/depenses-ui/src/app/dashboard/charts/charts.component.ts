import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {

  constructor() { }

  @Input('categories')
  public categories : any[];

  public categoriesLabels : String[];


  ngOnInit(): void {
      this.categoriesLabels = this.categories.map(c => c.categoryLabel);
  }

    public chartType: string = 'pie';
    public chartDatasets: Array<any> = [
      { data: [2000,1950, 2500,3400,1980,2600,2200,1752], label:'My Charges' }
    ];


    public chartOptions: any = {
      responsive: true
    };
    public chartClicked(e: any): void { }
    public chartHovered(e: any): void { }
}
