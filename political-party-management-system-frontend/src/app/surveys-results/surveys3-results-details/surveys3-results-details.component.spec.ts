import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys3ResultsDetailsComponent } from './surveys3-results-details.component';

describe('Surveys3ResultsDetailsComponent', () => {
  let component: Surveys3ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys3ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys3ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys3ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
