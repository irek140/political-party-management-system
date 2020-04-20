import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey6DetailsComponent } from './survey6-details.component';

describe('Survey6DetailsComponent', () => {
  let component: Survey6DetailsComponent;
  let fixture: ComponentFixture<Survey6DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey6DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey6DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
