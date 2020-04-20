import { Component, OnInit, Input } from '@angular/core';
import { User } from '../model/User';
import { UsersListComponent } from '../users-list/users-list.component';
import { UserService } from '../services/user.service';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../services/authentication.service';
import { RegionService } from '../services/region.service';
import { Observable } from 'rxjs';
import { Region } from '../model/Region';
import { ConstituencyService } from '../services/constituency.service';
import { Constituency } from '../model/Constituency';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  @Input()
  user: User;

  updateUser: User;

  edit: boolean;
  hiddenForm: boolean;
  checked: boolean;

  userDetails: boolean;

  region: string;
  constituency: string;

  partyPresident: boolean; //prezes partii
  nationalBoard: boolean; //zarząd krajowy
  nationalCouncil: boolean; //rada krajowa
  partyCourt: boolean; //sąd partyjny

  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  allConstituencies: Observable<Constituency[]>;

  isAdmin: boolean;

  constructor(private userService: UserService, private usersList: UsersListComponent, private constituencyService: ConstituencyService, private regionservice: RegionService,  private toastService: ToastrService) { }

  ngOnInit() {
    this.isAdmin = false;
    this.getRegions();
    this.getAllConstituencies();
    this.initUpdateUser();
    this.edit = false;
    this.hiddenForm = true;
    this.checked = false;
    this.userDetails = false;
    this.partyPresident = this.user.partyPresident;
    this.nationalBoard = this.user.nationalBoard;
    this.nationalCouncil = this.user.nationalCouncil;
    this.partyCourt = this.user.partyCourt;
    this.region = this.user.region;
    this.constituency = this.user.constituency;
    this.getMe();
    console.log(this.user.username);
    console.log(this.isAdmin);
  }

  getMe() {
        if (this.user.username === 'admin') {
          this.isAdmin = true;
        } else {
          this.isAdmin = false;
        }
  }

  getAllConstituencies() {
    this.allConstituencies = this.constituencyService.getAllConstituency();
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }

  updateActive(isActive: boolean) {
    this.userService.updateUser(this.user.id,
      { enabled: isActive })
      .subscribe(
        data => {
          console.log(data);
          this.user = data as User;
        },
        error => console.log(error));
  }

  editUser() {
    this.edit = !this.edit;
    this.hiddenForm = !this.hiddenForm;
    console.log(this.user);
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.id).subscribe();
    this.userDetails = true;
  }

  initUpdateUser() {
    this.updateUser = { username: this.user.username, password: this.user.password, email: this.user.email, name: this.user.name, surname: this.user.surname, region: this.user.region, constituency: this.user.constituency, enabled: this.user.enabled, partyPresident: this.user.partyPresident, nationalBoard: this.user.nationalBoard, nationalCouncil: this.user.nationalCouncil, partyCourt: this.user.partyCourt };
  }

  updateUserMethod() {
    this.userService.updateUserProperties(this.user.id,
      this.updateUser)
      .subscribe(
        data => {
          console.log(data);
          this.user = data as User;
          this.showSuccessAlert();
        },
        error => console.log(error));
  }

  showSuccessAlert() {
    this.toastService.success('Pomyślnie zaktualizowano!', 'SUKCES!');
    this.edit = !this.edit;
    this.hiddenForm = !this.hiddenForm;
  }

  banUser() {
    this.userService.banUser(this.user.id)
      .subscribe(
        data => {
          console.log(data);
          this.user = data as User;
        },
        error => console.log(error));
  }


}
