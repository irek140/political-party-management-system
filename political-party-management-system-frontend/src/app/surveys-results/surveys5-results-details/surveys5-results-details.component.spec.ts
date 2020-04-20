import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys5ResultsDetailsComponent } from './surveys5-results-details.component';

describe('Surveys5ResultsDetailsComponent', () => {
  let component: Surveys5ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys5ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys5ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys5ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
