import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Kweet} from "../kweet/kweet";
import {HttpClient} from "@angular/common/http";
import {Owner} from "../kweet/owner";

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  username: string;
  private sub: any;

  user: Owner;

  kweets: Kweet[];

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.username = params['username']; // (+) converts string 'id' to a number

      // In a real app: dispatch action to load the details here.
    });

    if (!this.username)
    {
      this.username = "user1";
    }

    this.http.get<Owner>('http://localhost:8080/kwetter/rest/user/' + this.username ).subscribe(data => {
        console.log(data);
        this.user = data;
      },
      err => {
        console.log("Error occured.")
      }
    );

    this.http.get<Kweet[]>('http://localhost:8080/kwetter/rest/user/' + this.username + '/kweets' ).subscribe(data => {
        console.log(data);
        this.kweets = data;
      },
      err => {
      this.kweets = [];
        console.log("Error occured.")
      }
    );

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
