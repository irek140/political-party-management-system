import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-edit-for-region-president',
  templateUrl: './edit-for-region-president.component.html',
  styleUrls: ['./edit-for-region-president.component.css']
})
export class EditForRegionPresidentComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersForRegionPresident();
  }

}
