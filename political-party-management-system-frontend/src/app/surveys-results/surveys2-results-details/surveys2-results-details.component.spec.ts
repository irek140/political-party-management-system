import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys2ResultsDetailsComponent } from './surveys2-results-details.component';

describe('Surveys2ResultsDetailsComponent', () => {
  let component: Surveys2ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys2ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys2ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys2ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
