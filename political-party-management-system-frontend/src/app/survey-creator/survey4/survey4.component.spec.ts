import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey4Component } from './survey4.component';

describe('Survey4Component', () => {
  let component: Survey4Component;
  let fixture: ComponentFixture<Survey4Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey4Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
