import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForRegionPresidentDetailsComponent } from './edit-for-region-president-details.component';

describe('EditForRegionPresidentDetailsComponent', () => {
  let component: EditForRegionPresidentDetailsComponent;
  let fixture: ComponentFixture<EditForRegionPresidentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForRegionPresidentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForRegionPresidentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
