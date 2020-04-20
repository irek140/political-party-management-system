import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../../model/Task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-sent-list',
  templateUrl: './task-sent-list.component.html',
  styleUrls: ['./task-sent-list.component.css']
})
export class TaskSentListComponent implements OnInit {

  tasks: Observable<Task[]>;

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.tasks = this.taskService.getMySentList();
  }
}
