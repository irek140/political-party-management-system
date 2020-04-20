import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForRegionSecretaryDetailsComponent } from './edit-for-region-secretary-details.component';

describe('EditForRegionSecretaryDetailsComponent', () => {
  let component: EditForRegionSecretaryDetailsComponent;
  let fixture: ComponentFixture<EditForRegionSecretaryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForRegionSecretaryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForRegionSecretaryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
