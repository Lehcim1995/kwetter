import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FrontpageTrendsComponent} from './frontpage-trends.component';

describe('FrontpageTrendsComponent', () => {
  let component: FrontpageTrendsComponent;
  let fixture: ComponentFixture<FrontpageTrendsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FrontpageTrendsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrontpageTrendsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
