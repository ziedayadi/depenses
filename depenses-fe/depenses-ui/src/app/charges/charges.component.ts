import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {Charge, ChargeType, CreateNewChargeRequest, Periods, ChargeCategory} from "./models";
import {ChargesService} from "./charges.service";
import {MatSort} from "@angular/material/sort";
import {DATE_FORMAT} from "../const";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-charges',
  templateUrl: './charges.component.html',
  styleUrls: ['./charges.component.css']
})
export class ChargesComponent implements OnInit {

  @ViewChild('periodicSort', {static: false}) periodicSort: MatSort;
  @ViewChild('oneTimeSort', {static: false}) oneTimeSort: MatSort;

  dateFormat = DATE_FORMAT;

  periodicChargesDisplayedColumns: string[] = ['label', 'amount', 'category', 'period', 'startDate', 'endDate','active','edit','delete'];
  oneTimeChargesDisplayedColumns: string[] = ['label', 'amount', 'category', 'effectDate','edit','delete'];

  periodicChargesDatasource : MatTableDataSource<Charge> ;
  onetimeChargesDatasource : MatTableDataSource<Charge> ;
  private periodicChares : Charge[];
  private oneTimeCharges : Charge[];

  constructor(private loginService : LoginService,
              private chargesService: ChargesService,
              private router : Router,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    if(! this.loginService.user){
      this.router.navigateByUrl('/');
      return;
    }

    this.initData();
  }
  onCreateNewCharge(){
    let req = new CreateNewChargeRequest();
    req.userId = this.loginService.user.id;


    const dialogRef = this.dialog.open(CreateNewChargeDialog, {
      width: '600px',
      data: {request: req}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.initData();
    });

  }

  /**
   * onEdit(row)
    */
  public onEdit(row) {
    let req = new CreateNewChargeRequest();
    req.categoryId = row.category.id;
    req.chargeId = row.id;
    req.label = row.label;
    req.description = row.description;
    req.amount = row.amount;
    req.type = row.type;
    req.effectDate = new Date(row.effectDate);
    req.period = row.period;
    req.startDate =  new Date(row.startDate);
    req.endDate = new Date(row.endDate);
    req.active = row.active;
    req.userId = this.loginService.user.id;
    console.log(req);

    const dialogRef = this.dialog.open(CreateNewChargeDialog, {
      width: '600px',
      data: {userId: this.loginService.user.id ,
            request: req }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.initData();
    });
  }

  onDelete(row) {
    this.chargesService.delete(row.id).subscribe(response => {
        this.initData();
    })
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
@Component({
  selector: 'create-periodic-charge-dialog',
  templateUrl: 'create-new-charge-dialog.html',
})
export class CreateNewChargeDialog implements OnInit  {

  types : String [] =  [ChargeType.PERIODIC , ChargeType.ONE_TIME];
  periods : String [] =  [Periods.WEEK,Periods.MONTH,Periods.YEAR];
  categories : ChargeCategory[];


  createNewChargeRequest : CreateNewChargeRequest ;
  constructor(
    public dialogRef: MatDialogRef<CreateNewChargeDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private chargesService: ChargesService) {}

    ngOnInit(): void {
      this.createNewChargeRequest = this.data.request;
      this.chargesService.getAllCategories().subscribe(response => {
          this.categories = response;
      })
    }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(){
    this.chargesService.createNewCharge(this.createNewChargeRequest).subscribe(response => {
      this.dialogRef.close();
    })
  }


  public getChargeTypeLabel(chargeCode : String) : String {
    switch (chargeCode) {
      case ChargeType.ONE_TIME:
        return 'One time'
      case ChargeType.PERIODIC :
      return 'Periodic'
      default:
        return null;
    }
  }

}
