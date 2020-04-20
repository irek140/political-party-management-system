import { Component, OnInit, Input } from '@angular/core';
import { Task } from 'src/app/model/Task';
import { User } from 'src/app/model/User';
import { TaskSentListComponent } from '../task-sent-list/task-sent-list.component';
import { TaskService } from 'src/app/services/task.service';


@Component({
  selector: 'app-task-sent-details',
  templateUrl: './task-sent-details.component.html',
  styleUrls: ['./task-sent-details.component.css']
})
export class TaskSentDetailsComponent implements OnInit {

  @Input()
  task: Task;

  accepted: boolean;
  done: boolean;

  status: string;

  addressessList: User[];

  hiddenDiv: boolean;

  constructor(private taskService: TaskService, private taskSentList: TaskSentListComponent) { }

  ngOnInit() {
    this.hiddenDiv = false;
    this.accepted = this.task.inProgress;
    this.done = this.task.done;
    this.addressessList = this.task.addressees;
    this.changeStatus();
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

  deleteTask() {
    this.hiddenDiv = true;
    this.taskService.deleteTask(this.task.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  correctTask() {
    this.taskService.correctTask(this.task.id).subscribe(
      data => {
        this.status = 'Przydzielone';
        this.done = false
        this.accepted = false
        console.log(data);
      }, error => console.log(error)
    );
  }

  removeInOwner() {
    this.hiddenDiv = true;
    this.taskService.removeInOwner(this.task.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }
}
