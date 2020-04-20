import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys6ResultsDetailsComponent } from './surveys6-results-details.component';

describe('Surveys6ResultsDetailsComponent', () => {
  let component: Surveys6ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys6ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys6ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys6ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
