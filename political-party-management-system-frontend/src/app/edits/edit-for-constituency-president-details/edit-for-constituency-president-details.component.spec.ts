import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForConstituencyPresidentDetailsComponent } from './edit-for-constituency-president-details.component';

describe('EditForConstituencyPresidentDetailsComponent', () => {
  let component: EditForConstituencyPresidentDetailsComponent;
  let fixture: ComponentFixture<EditForConstituencyPresidentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForConstituencyPresidentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForConstituencyPresidentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
