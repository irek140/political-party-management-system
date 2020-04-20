import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinMessageListComponent } from './join-message-list.component';

describe('JoinMessageListComponent', () => {
  let component: JoinMessageListComponent;
  let fixture: ComponentFixture<JoinMessageListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinMessageListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinMessageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
