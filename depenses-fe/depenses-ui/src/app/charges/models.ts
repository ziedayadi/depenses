export class ChargeType {
  public static PERIODIC = 'PERIODIC';
  public static ONE_TIME = 'ONE_TIME';
}

export interface ChargeCategory {
  id: number;
  code: String;
  label: String;
}

export class Periods {
  public static MONTH;
  public static WEEK;
  public static YEAR;

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
}

export interface SearchChargesRequest {
  userId: number;
}

export class CreateNewChargeRequest {
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
  active : boolean ;
}
