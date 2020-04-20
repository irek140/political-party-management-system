import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskSentDetailsComponent } from './task-sent-details.component';

describe('TaskSentDetailsComponent', () => {
  let component: TaskSentDetailsComponent;
  let fixture: ComponentFixture<TaskSentDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskSentDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskSentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
