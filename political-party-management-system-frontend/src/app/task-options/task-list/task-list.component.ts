import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../../model/Task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Observable<Task[]>;

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.tasks = this.taskService.getMyTasksList();
  }

}
