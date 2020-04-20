import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Surveys7ResultsDetailsComponent } from './surveys7-results-details.component';

describe('Surveys7ResultsDetailsComponent', () => {
  let component: Surveys7ResultsDetailsComponent;
  let fixture: ComponentFixture<Surveys7ResultsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Surveys7ResultsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Surveys7ResultsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
