import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskSentListComponent } from './task-sent-list.component';

describe('TaskSentListComponent', () => {
  let component: TaskSentListComponent;
  let fixture: ComponentFixture<TaskSentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskSentListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskSentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
