import {Directive, OnDestroy, OnInit} from '@angular/core';

@Directive({
  selector: '[test]'
})
export class TestDirective implements OnInit, OnDestroy {

  constructor() {
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }
}
