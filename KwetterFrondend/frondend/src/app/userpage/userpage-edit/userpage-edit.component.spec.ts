import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UserpageEditComponent} from './userpage-edit.component';

describe('UserpageEditComponent', () => {
  let component: UserpageEditComponent;
  let fixture: ComponentFixture<UserpageEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserpageEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserpageEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
