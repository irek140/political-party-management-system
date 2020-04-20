import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForPresidentDetailsComponent } from './edit-for-president-details.component';

describe('EditForPresidentDetailsComponent', () => {
  let component: EditForPresidentDetailsComponent;
  let fixture: ComponentFixture<EditForPresidentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForPresidentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForPresidentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
