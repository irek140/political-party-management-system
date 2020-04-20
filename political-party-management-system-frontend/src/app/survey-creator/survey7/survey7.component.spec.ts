import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey7Component } from './survey7.component';

describe('Survey7Component', () => {
  let component: Survey7Component;
  let fixture: ComponentFixture<Survey7Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey7Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey7Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
