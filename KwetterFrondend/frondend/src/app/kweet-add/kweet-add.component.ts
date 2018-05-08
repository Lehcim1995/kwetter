import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-kweet-add',
  templateUrl: './kweet-add.component.html',
  styleUrls: ['./kweet-add.component.css']
})
export class KweetAddComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  message;
  owner;

  ngOnInit() {
  }

  method()
  {

    this.http.post('http://localhost:8080/kwetter/rest/kweet', { "message" : this.message, "username" :this.owner }).subscribe(data => {
        console.log(data);
        this.router.navigate(['']);
      },
      err => {
        console.log("Error occured.")

      }
    );

    console.log(this.message);
    console.log(this.owner);
  }

}
