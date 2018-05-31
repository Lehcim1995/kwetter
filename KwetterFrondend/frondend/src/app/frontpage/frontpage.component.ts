import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Kweet} from "../kweet/kweet";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrls: ['./frontpage.component.css']
})
export class FrontpageComponent implements OnInit {

  number = []; // TODO remove
  kweets = [];

  kweetmessage: string;

  userLink: string;

  CHAT_URL = 'ws://localhost:8080/kwetter/kweetstream';
  ws: any;

  constructor(private http: HttpClient, public auth: AuthService) {
  }

  ngOnInit() { //https://codingthesmartway.com/angular-4-3-httpclient-accessing-rest-web-services-with-angular/

    this.http.get<Kweet[]>('http://localhost:8080/kwetter/rest/kweet').subscribe(data => {
        console.log(data);
        this.kweets = data;
        // this.number = this.kweets.map(value => value.message);

      },
      err => {
        console.log("Error occured.")
      }
    );

    this.ws = new WebSocket(this.CHAT_URL);
    this.ws.onmessage =  (message) => this.updateKweets(message);
  }

  onKeydown(event) {


    if (event.key === "Enter" && this.auth.isAuthenticated()) {
      console.log(event);
      window.alert(this.kweetmessage);

      // todo make this

      this.kweetmessage = "";
    }

    if (event.key === "Enter" && !this.auth.isAuthenticated())
    {
      window.alert("please log in");
    }
  }

  public updateKweets(message) {
    let parsed = JSON.parse(message.data);

    if (parsed instanceof Array) {
      this.kweets = parsed;
    }
    else
    {
      this.kweets.unshift(parsed);
    }
  }

  ngOnClose()
  {
    this.ws.close();
  }
}
