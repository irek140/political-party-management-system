import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForSecretaryComponent } from './edit-for-secretary.component';

describe('EditForSecretaryComponent', () => {
  let component: EditForSecretaryComponent;
  let fixture: ComponentFixture<EditForSecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForSecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForSecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
