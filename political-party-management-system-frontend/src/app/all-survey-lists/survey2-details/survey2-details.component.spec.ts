import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey2DetailsComponent } from './survey2-details.component';

describe('Survey2DetailsComponent', () => {
  let component: Survey2DetailsComponent;
  let fixture: ComponentFixture<Survey2DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey2DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey2DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
