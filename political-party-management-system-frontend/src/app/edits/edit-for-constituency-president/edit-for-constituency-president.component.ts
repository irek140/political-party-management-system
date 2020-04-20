import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-edit-for-constituency-president',
  templateUrl: './edit-for-constituency-president.component.html',
  styleUrls: ['./edit-for-constituency-president.component.css']
})
export class EditForConstituencyPresidentComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersForConstituencyPresident();
  }

}
