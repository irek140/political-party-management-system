import { Component, OnInit } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { UserService } from '../../services/user.service';
import { User } from '../../model/User';
import { ToastrService } from 'ngx-toastr';
import { Region } from 'src/app/model/Region';
import { Observable } from 'rxjs';
import { RegionService } from 'src/app/services/region.service';

@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  title: string;
  content: string;

  addresses: User[];
  recipient: User;

  users: User[];
  user: User;
  username: string;
  email: string;
  name: string;
  surname: string;
  region: string;
  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];

  editorConfig = {
    editable: true,
    spellcheck: false,
    height: '20rem',
    minHeight: '5rem',
    placeholder: 'Napisz wiadomość...',
    translate: 'no',
  };

  constructor(private messageService: MessageService, private userService: UserService, private regionservice: RegionService, private toastService: ToastrService) { }

  ngOnInit() {
    this.getRegions();
    this.users = [];
    this.addresses = [];
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }

  searchUserByUsername() {
    this.userService.getUserByUsername(this.username)
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

  sendMessage() {
    this.messageService.createMessage( {title: this.title, content: this.content, addressees: this.addresses} ).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.title = '';
        this.content = '';
      },
      error => console.log(error));
  }

  addAddresses(user: User) {
      //this.addresses = this.addresses.filter( (el: User) => el !== user);
      //console.log(this.addresses.indexOf(user));
      console.log(user);
      this.addresses = this.addresses.filter( (el: User) => el.username !== user.username);
      this.addresses.push(user);
      this.users = this.users.filter(obj => obj !== user);
      this.user = null;
      console.log(this.addresses);
  }

  removeAddresses(user: User) {
    this.addresses = this.addresses.filter(obj => obj !== user);
    console.log(this.addresses);
  }

  showSuccessAlert() {
    this.toastService.success('Wiadomość wysłana!', 'SUKCES!');
  }

}
