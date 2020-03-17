import {Component, OnInit} from '@angular/core';
import {LoginRequest, LoginService} from "./login.service";
import {DASHBOARD, LOGIN, SUCCESS} from "../const";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest = new LoginRequest();
  loginMessage: String;

  constructor(private loginService: LoginService,
              private router : Router) {
  }

  ngOnInit() {
  }

  doLogin() {
    this.loginService.login(this.loginRequest).subscribe(response => {
      let result = response.result;
      if (result == SUCCESS) {
        this.loginService.user = response.user;
        this.router.navigateByUrl('/'+DASHBOARD)
      } else {
        this.loginMessage = response.result;
      }

    })
  }

  getTranscriptedMessageFrom(messageCode: String): String {
    switch (messageCode) {
      case 'USER_NOT_FOUND' : {
        return 'Login failed: User is not found'
      }
      case 'WRONG_PASSWORD' : {
        return 'Login failed: Wrong password'
      }
      default: {
        return 'Login failed: Unknown message'
      }
    }
  }

}
