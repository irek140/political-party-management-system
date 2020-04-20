import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForConstituencySecretaryDetailsComponent } from './edit-for-constituency-secretary-details.component';

describe('EditForConstituencySecretaryDetailsComponent', () => {
  let component: EditForConstituencySecretaryDetailsComponent;
  let fixture: ComponentFixture<EditForConstituencySecretaryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForConstituencySecretaryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForConstituencySecretaryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
