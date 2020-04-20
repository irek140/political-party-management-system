import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys4ResultsDetailsComponent } from './surveys4-results-details.component';

describe('Surveys4ResultsDetailsComponent', () => {
  let component: Surveys4ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys4ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys4ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys4ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
