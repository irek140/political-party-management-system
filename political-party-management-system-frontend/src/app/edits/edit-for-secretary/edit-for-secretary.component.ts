import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-edit-for-secretary',
  templateUrl: './edit-for-secretary.component.html',
  styleUrls: ['./edit-for-secretary.component.css']
})
export class EditForSecretaryComponent implements OnInit {


  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersForPresident();
  }

}
