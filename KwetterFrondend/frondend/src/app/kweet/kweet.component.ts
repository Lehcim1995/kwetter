import {Component, Input, OnInit} from '@angular/core';
import {Kweet} from "./kweet";
import {Owner} from "./owner";

@Component({
  selector: 'app-kweet',
  templateUrl: './kweet.component.html',
  styleUrls: ['./kweet.component.css']
})
export class KweetComponent implements OnInit {

  constructor() { }

  owner: Owner;

  @Input() kweet: Kweet;

  ngOnInit() {
  }

}
