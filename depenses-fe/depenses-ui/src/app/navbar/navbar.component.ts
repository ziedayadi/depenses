import {Component, OnInit} from '@angular/core';
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  selectedMenu = "DASHBOARD";
  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
  }


  getConnectedUser(): any {
    return this.loginService.user;
  }

  onSelectMenu(menuName : String){
    this.selectedMenu = menuName;
  }
}
