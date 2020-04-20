import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey7DetailsComponent } from './survey7-details.component';

describe('Survey7DetailsComponent', () => {
  let component: Survey7DetailsComponent;
  let fixture: ComponentFixture<Survey7DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey7DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey7DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
