import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinMessageDetailsComponent } from './join-message-details.component';

describe('JoinMessageDetailsComponent', () => {
  let component: JoinMessageDetailsComponent;
  let fixture: ComponentFixture<JoinMessageDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinMessageDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinMessageDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
