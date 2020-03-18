import {Component, OnInit} from '@angular/core';
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  constructor(private loginService: LoginService) {
  }

  connectedUser: any;

  ngOnInit(): void {
    this.connectedUser = this.loginService.user;
    console.log(this.loginService.user)
  }

  getUser(): any {
    return this.loginService.user;
  }

}
