import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-frontpage-trends',
  templateUrl: './frontpage-trends.component.html',
  styleUrls: ['./frontpage-trends.component.css']
})
export class FrontpageTrendsComponent implements OnInit {

  constructor(private http: HttpClient) { }

  trends: string[];

  ngOnInit() {
    this.trends = ['#leuk', '#jea' , '#trending', '#awesome', '#java']

    this.http.get<string[]>('http://localhost:8080/kwetter/rest/kweet/trends').subscribe(data => {
        this.trends = data;

      },
      err => {
        console.log("Error occured.")
      }
    );
  }

}
