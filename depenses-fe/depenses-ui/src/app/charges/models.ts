export class ChargeType {
  public static PERIODIC = 'PERIODIC';
  public static ONE_TIME = 'ONE_TIME';
}

export class Periods{
  public static WEEK = 'WEEK';
  public static MONTH = 'MONTH';
  public static YEAR = 'YEAR';
}

export interface ChargeCategory {
  id: number;
  code: String;
  label: String;
}

export interface Charge {
  id: number;
  type: String;
  label: String;
  description: String;
  amount: number;
  userId: number;
  category: ChargeCategory;
  effectDate: Date;
  period: String;
  startDate: Date;
  endDate: Date;
  active: boolean;
  debitDate:Date;
}

export interface SearchChargesRequest {
  userId: number;
  startDate : Date;
  endDate : Date;
}

export class CreateNewChargeRequest {
  chargeId : number;
  userId : number;
  categoryId : number;
  label : String;
  description : String;
  amount : number;
  type : String;
  effectDate : Date;
  period : String;
  startDate : Date;
  endDate : Date;
  active : boolean = true;
}
