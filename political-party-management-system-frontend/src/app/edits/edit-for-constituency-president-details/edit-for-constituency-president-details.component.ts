import { Component, OnInit, Input } from '@angular/core';

import { EditForConstituencyPresidentComponent } from '../edit-for-constituency-president/edit-for-constituency-president.component';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';
import { Observable } from 'rxjs';
import { Region } from 'src/app/model/Region';
import { RegionService } from 'src/app/services/region.service';
import { ConstituencyService } from 'src/app/services/constituency.service';
import { Constituency } from 'src/app/model/Constituency';

@Component({
  selector: 'app-edit-for-constituency-president-details',
  templateUrl: './edit-for-constituency-president-details.component.html',
  styleUrls: ['./edit-for-constituency-president-details.component.css']
})
export class EditForConstituencyPresidentDetailsComponent implements OnInit {

  @Input()
  user: User;

  updateUser: User;

  edit: boolean;
  hiddenForm: boolean;
  checked: boolean;

  userDetails: boolean;
  region: string;
  constituency: string;

  constituencySecretary: boolean;
  constituencyVicePresident: boolean;

  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];
  allConstituencies: Observable<Constituency[]>;

  constructor(private userService: UserService, private usersList: EditForConstituencyPresidentComponent, private constituencyService: ConstituencyService, private regionservice: RegionService, private toastService: ToastrService) { }

  ngOnInit() {
    this.getRegions();
    this.getAllConstituencies();
    this.initUpdateUser();
    this.edit = false;
    this.hiddenForm = true;
    this.checked = false;
    this.userDetails = false;
    this.region = this.user.region;
    this.constituency = this.user.constituency;

    this.constituencyVicePresident = this.user.constituencyVicePresident;
    this.constituencySecretary = this.user.constituencySecretary;
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
    this.updateUser = { username: this.user.username, password: this.user.password, email: this.user.email, name: this.user.name, surname: this.user.surname, region: this.user.region, constituency: this.user.constituency, enabled: this.user.enabled, constituencyVicePresident: this.user.constituencyVicePresident, constituencySecretary: this.user.constituencySecretary};
  }

  updateUserMethod() {
    this.userService.updateUserFromConstituencyPresident(this.user.id,
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
