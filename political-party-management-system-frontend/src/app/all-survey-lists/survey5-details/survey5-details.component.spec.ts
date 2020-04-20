import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey5DetailsComponent } from './survey5-details.component';

describe('Survey5DetailsComponent', () => {
  let component: Survey5DetailsComponent;
  let fixture: ComponentFixture<Survey5DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey5DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey5DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
