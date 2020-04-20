import { Component, OnInit, Input } from '@angular/core';
import { Task } from '../../model/Task';
import { TaskService } from '../../services/task.service';
import { TaskListComponent } from '../task-list/task-list.component';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  @Input()
  task: Task;

  accepted: boolean;
  done: boolean;

  status: string;

  hiddenDiv: boolean;

  report: string;

  editorConfig = {
    editable: true,
    spellcheck: false,
    height: '20rem',
    minHeight: '5rem',
    placeholder: 'Treść zadania...',
    translate: 'no',
  };

  constructor(private taskService: TaskService, private taskList: TaskListComponent) { }

  ngOnInit() {
    this.hiddenDiv = false;
    this.accepted = this.task.inProgress;
    this.done = this.task.done;
    this.changeStatus();
  }

  sendReport() {
    console.log(this.task);
    this.taskService.createReport(this.task).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  changeStatus() {
    if (!this.accepted && !this.done) {
      this.status = 'Przydzielone';
    }
    if (this.accepted && !this.done) {
      this.status = 'W trakcie realizacji';
    }
    if (this.accepted && this.done) {
      this.status = 'Wykonane';
    }
  }

  removeTask() {
    this.hiddenDiv = true;
    this.taskService.removeTask(this.task.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  acceptTask() {
    this.taskService.acceptTask(this.task.id).subscribe(
      data => {
        this.status = 'W trakcie realizacji';
        this.accepted = true;
        console.log(data);
      }, error => console.log(error)
    );
  }

  finishTask() {
    this.taskService.finishTask(this.task.id).subscribe(
      data => {
        this.status = 'Wykonane';
        this.done = true;
        console.log(data);
      }, error => console.log(error)
    );
  }
}
