import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForSecretaryDetailsComponent } from './edit-for-secretary-details.component';

describe('EditForSecretaryDetailsComponent', () => {
  let component: EditForSecretaryDetailsComponent;
  let fixture: ComponentFixture<EditForSecretaryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForSecretaryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForSecretaryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
