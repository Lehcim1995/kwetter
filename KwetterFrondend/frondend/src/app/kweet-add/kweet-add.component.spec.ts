import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {KweetAddComponent} from './kweet-add.component';

describe('KweetAddComponent', () => {
  let component: KweetAddComponent;
  let fixture: ComponentFixture<KweetAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KweetAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KweetAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
