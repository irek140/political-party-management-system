import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Survey6Component } from './survey6.component';

describe('Survey6Component', () => {
  let component: Survey6Component;
  let fixture: ComponentFixture<Survey6Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Survey6Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Survey6Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
