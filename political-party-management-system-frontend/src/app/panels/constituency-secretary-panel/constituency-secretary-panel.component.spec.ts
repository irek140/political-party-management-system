import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstituencySecretaryComponent } from './constituency-secretary-panel.component';

describe('ConstituencySecretaryComponent', () => {
  let component: ConstituencySecretaryComponent;
  let fixture: ComponentFixture<ConstituencySecretaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstituencySecretaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstituencySecretaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
