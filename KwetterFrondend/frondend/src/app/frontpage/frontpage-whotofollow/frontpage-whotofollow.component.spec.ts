import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FrontpageWhotofollowComponent} from './frontpage-whotofollow.component';

describe('FrontpageWhotofollowComponent', () => {
  let component: FrontpageWhotofollowComponent;
  let fixture: ComponentFixture<FrontpageWhotofollowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FrontpageWhotofollowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrontpageWhotofollowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
