import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { UserService } from '../../services/user.service';
import { TaskService } from '../../services/task.service';
import { ToastrService } from 'ngx-toastr';
import { Region } from 'src/app/model/Region';
import { Observable } from 'rxjs';
import { RegionService } from 'src/app/services/region.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  taskTitle: string;
  taskContent: string;

  addresses: User[];

  users: User[];
  user: User;
  username: string;
  email: string;
  name: string;
  surname: string;
  region: string;
  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];

  minDate: Date;
  startdate: Date;
  enddate: Date;

  editorConfig = {
    editable: true,
    spellcheck: false,
    height: '20rem',
    minHeight: '5rem',
    placeholder: 'Treść zadania...',
    translate: 'no',
  };

  constructor(private taskService: TaskService, private userService: UserService, private regionservice: RegionService, private toastService: ToastrService) { }

  ngOnInit() {
    this.getRegions();
    this.users = [];
    this.addresses = [];
    this.minDate = new Date();
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }


  searchUserByUsernameWithoutAdmin() {
    this.userService.getUserByUsernameWithoutAdmin(this.username)
      .subscribe( user => this.user = user );
    this.username = null;
    this.users = [];
  }

  searchUserByEmail() {
    this.userService.getUserByEmail(this.email)
      .subscribe( user => this.user = user );
    this.email = null;
    this.users = [];
  }

  searchUserByName() {
    this.userService.getUserByName(this.name)
      .subscribe( users => this.users = users );
    this.name = null;
    this.user = null;
  }

  searchUserBySurname() {
    this.userService.getUserBySurname(this.surname)
      .subscribe( users => this.users = users );
    this.surname = null;
    this.user = null;
  }

  searchUserByRegion() {
    this.userService.getUserByRegion(this.region)
      .subscribe( users => this.users = users );
    this.region = null;
    this.user = null;
  }

  assignTask() {
    console.log({taskTitle: this.taskTitle, taskContent: this.taskContent, addressees: this.addresses, startdate: this.startdate, enddate: this.enddate} );
    this.taskService.createTask( {taskTitle: this.taskTitle, taskContent: this.taskContent, addressees: this.addresses, startdate: this.startdate, enddate: this.enddate} ).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.taskTitle = '';
        this.taskContent = '';
        this.startdate = null;
        this.enddate = null;
      },
      error => console.log(error));
  }

  addAddresses(user: User) {
    this.addresses = this.addresses.filter( (el: User) => el.username !== user.username);
    this.addresses.push(user);
    console.log(this.addresses);
    this.users = this.users.filter(obj => obj !== user);
    this.user = null;
  }

  removeAddresses(user: User) {
    this.addresses = this.addresses.filter(obj => obj !== user);
    console.log(this.addresses);
  }

  showSuccessAlert() {
    this.toastService.success('Zlecono zadanie!', 'SUKCES!');
  }

}
