import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey3DetailsComponent } from './survey3-details.component';

describe('Survey3DetailsComponent', () => {
  let component: Survey3DetailsComponent;
  let fixture: ComponentFixture<Survey3DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey3DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey3DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
