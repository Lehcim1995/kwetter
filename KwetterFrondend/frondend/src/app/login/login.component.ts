///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() invalidInput = false;
  @Input() noInput = false;

  username: string;
  password: string;
  expires: boolean = false;

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.checkLogin();
  }

  login() {
    this.invalidInput = false;
    this.noInput = false;
    if (this.username && this.password) {
      this.auth.authenticate(this.username, this.password, this.expires).subscribe(
        (res) => {
          this.auth.setAuthKey(res.headers.get('Auth'), this.username);
          this.router.navigate(['']);
        }
      );
    }
    else {
      this.noInput = true;
    }
  }

  checkLogin() {
    if (this.auth.isAuthenticated()) {
      this.router.navigate(['']);
    }
  }


}
