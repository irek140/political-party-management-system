import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForPresidentComponent } from './edit-for-president.component';

describe('EditForPresidentComponent', () => {
  let component: EditForPresidentComponent;
  let fixture: ComponentFixture<EditForPresidentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForPresidentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForPresidentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
