import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForRegionSecretaryComponent } from './edit-for-region-secretary.component';

describe('EditForRegionSecretaryComponent', () => {
  let component: EditForRegionSecretaryComponent;
  let fixture: ComponentFixture<EditForRegionSecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForRegionSecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForRegionSecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
