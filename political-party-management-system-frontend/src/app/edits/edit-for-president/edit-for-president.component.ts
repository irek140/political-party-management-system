import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-edit-for-president',
  templateUrl: './edit-for-president.component.html',
  styleUrls: ['./edit-for-president.component.css']
})
export class EditForPresidentComponent implements OnInit {


  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersForPresident();
  }

}
