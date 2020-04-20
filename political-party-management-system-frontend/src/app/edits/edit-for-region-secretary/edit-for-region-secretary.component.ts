import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-for-region-secretary',
  templateUrl: './edit-for-region-secretary.component.html',
  styleUrls: ['./edit-for-region-secretary.component.css']
})
export class EditForRegionSecretaryComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersForRegionPresident();
  }

}
