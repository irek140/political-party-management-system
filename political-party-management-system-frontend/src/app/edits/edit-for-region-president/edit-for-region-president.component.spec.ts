import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForRegionPresidentComponent } from './edit-for-region-president.component';

describe('EditForRegionPresidentComponent', () => {
  let component: EditForRegionPresidentComponent;
  let fixture: ComponentFixture<EditForRegionPresidentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForRegionPresidentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForRegionPresidentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
