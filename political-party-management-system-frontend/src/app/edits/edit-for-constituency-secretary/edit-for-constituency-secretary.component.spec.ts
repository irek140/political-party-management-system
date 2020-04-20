import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditForConstituencySecretaryComponent } from './edit-for-constituency-secretary.component';

describe('EditForConstituencySecretaryComponent', () => {
  let component: EditForConstituencySecretaryComponent;
  let fixture: ComponentFixture<EditForConstituencySecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditForConstituencySecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditForConstituencySecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
