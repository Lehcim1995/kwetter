import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {


  ngOnInit() {
    this.ws = new WebSocket(this.CHAT_URL);
    this.ws.onmessage =  (message) => this.updateKweets(message);
  }

  CHAT_URL = 'ws://localhost:8080/kwetter/kweetstream';
  ws: any;
  lastMessage = 'nothing';

  kweets: any[] = [];

  constructor() {

  }

  public updateKweets(message) {
    this.lastMessage = message.data;

    let parsed = JSON.parse(message.data);

    console.log('Connection 1', parsed);

    if (parsed instanceof Array) {
      this.kweets = parsed;
    }
    else
    {
      this.kweets.unshift(parsed);
    }

    // let text = document.getElementById('lastMessage');
    // text.innerText = this.lastMessage;
  }

  ngOnChanges() {
    console.log('update');
    console.log('Connection 1', this.kweets);
  }

  receiveMessage() {

  }

  sendMessage() {
    console.log('Connection 1 sending', this.kweets);
    if (this.ws.readyState === WebSocket.OPEN) {
      console.log("send message");
      this.ws.send("get");
    }
  }
}
