import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveysResultsListComponent } from './surveys-results-list.component';

describe('SurveysResultsListComponent', () => {
  let component: SurveysResultsListComponent;
  let fixture: ComponentFixture<SurveysResultsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SurveysResultsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SurveysResultsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
