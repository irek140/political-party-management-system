import { Component, OnInit, Input } from '@angular/core';


import { ToastrService } from 'ngx-toastr';
import { EditForSecretaryComponent } from '../edit-for-secretary/edit-for-secretary.component';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';
import { Region } from 'src/app/model/Region';
import { RegionService } from 'src/app/services/region.service';
import { ConstituencyService } from 'src/app/services/constituency.service';
import { Constituency } from 'src/app/model/Constituency';

@Component({
  selector: 'app-edit-for-secretary-details',
  templateUrl: './edit-for-secretary-details.component.html',
  styleUrls: ['./edit-for-secretary-details.component.css']
})
export class EditForSecretaryDetailsComponent implements OnInit {

  @Input()
  user: User;

  updateUser: User;

  edit: boolean;
  hiddenForm: boolean;
  checked: boolean;

  userDetails: boolean;
  region: string;
  constituency: string;

  isPresident: boolean;

  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  allConstituencies: Observable<Constituency[]>;

  constructor(private userService: UserService, private usersList: EditForSecretaryComponent, private constituencyService: ConstituencyService, private regionservice: RegionService, private toastService: ToastrService) { }

  ngOnInit() {
    this.isPresidentMethod();
    this.getRegions();
    this.getAllConstituencies();
    this.initUpdateUser();
    this.edit = false;
    this.hiddenForm = true;
    this.checked = false;
    this.userDetails = false;
    this.region = this.user.region;
    this.constituency = this.user.constituency;
  }

  isPresidentMethod() {
    if (this.user.username === 'user') {
      this.isPresident = true;
    } else {
      this.isPresident = false;
    }
}

  getAllConstituencies() {
    this.allConstituencies = this.constituencyService.getAllConstituency();
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }


  editUser() {
    this.edit = !this.edit;
    this.hiddenForm = !this.hiddenForm;
  }

  initUpdateUser() {
    this.updateUser = { username: this.user.username, password: this.user.password, email: this.user.email, name: this.user.name, surname: this.user.surname, region: this.user.region, constituency: this.user.constituency, enabled: this.user.enabled};
  }

  updateUserMethod() {
    this.userService.updateUserFromPresident(this.user.id,
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

  choosePartyVicePresident() {
    this.userService.choosePartyVicePresident(this.user.id)
    .subscribe(
      data => {
        console.log(data);
        this.user = data as User;
      },
      error => console.log(error));
  }

  choosePartySecretary() {
    this.userService.choosePartySecretary(this.user.id)
    .subscribe(
      data => {
        console.log(data);
        this.user = data as User;
      },
      error => console.log(error));
  }
}
