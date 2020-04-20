import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey4DetailsComponent } from './survey4-details.component';

describe('Survey4DetailsComponent', () => {
  let component: Survey4DetailsComponent;
  let fixture: ComponentFixture<Survey4DetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey4DetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey4DetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
