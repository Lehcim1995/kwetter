import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-kweet-add',
  templateUrl: './kweet-add.component.html',
  styleUrls: ['./kweet-add.component.css']
})
export class KweetAddComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private auth: AuthService) {
  }

  message;
  owner;

  ngOnInit() {
  }

  method() {

    let owner;

    if (this.owner) {
      owner = this.owner;
    }
    else {
      owner = this.auth.getUserName();
    }

    this.http.post('http://localhost:8080/kwetter/rest/kweet', {
      "message": this.message,
      "username": owner
    }).subscribe(data => {
        console.log(data);
        this.router.navigate(['']);
      },
      err => {
        console.log("Error occured.")

      }
    );

    console.log(this.message);
    console.log(owner);
  }

}
