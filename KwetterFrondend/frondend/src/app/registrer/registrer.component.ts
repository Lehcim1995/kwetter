import {Component, OnInit} from '@angular/core';
import {Owner} from "../kweet/owner";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-registrer',
  templateUrl: './registrer.component.html',
  styleUrls: ['./registrer.component.css']
})
export class RegistrerComponent implements OnInit {

  username: string;
  password: string;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  register()
  {

    var modal: Owner = {
      username: this.username,
      password: this.password
    };


    this.http.post<Owner>('http://localhost:8080/kwetter/rest/user/', modal).subscribe(data => {
        console.log(data);
      },
      err => {
        console.log("Error occured.")
      }
    );
  }

}
