import { Component, OnInit,Input } from '@angular/core';
import {Charge } from "../models";
import {DATE_FORMAT} from "../../const";

@Component({
  selector: 'charge-card',
  templateUrl: './charge-card.component.html',
  styleUrls: ['./charge-card.component.css']
})
export class ChargeCardComponent implements OnInit {

  dateFormat = DATE_FORMAT;

  @Input('charge') charge : Charge;

  constructor() { }

  ngOnInit(): void {
    console.log(this.charge);

  }

}
