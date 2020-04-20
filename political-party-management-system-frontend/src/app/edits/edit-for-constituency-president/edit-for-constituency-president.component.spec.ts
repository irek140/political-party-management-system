import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForConstituencyPresidentComponent } from './edit-for-constituency-president.component';

describe('EditForConstituencyPresidentComponent', () => {
  let component: EditForConstituencyPresidentComponent;
  let fixture: ComponentFixture<EditForConstituencyPresidentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForConstituencyPresidentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForConstituencyPresidentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
