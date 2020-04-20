import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from '../model/User';
import { AuthenticationService } from '../services/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Region } from '../model/Region';
import { Observable } from 'rxjs';
import { RegionService } from '../services/region.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  user: User;

  regionName: string;
  regions: Observable<Region[]>;
  //regions: string[] = ['Podkarpackie', 'Małopolskie', 'Dolnośląskie', 'Kujawsko-Pomorskie', 'Lubelskie', 'Lubuskie', 'Łódzkie', 'Mazowieckie', 'Opolskie', 'Podlaskie', 'Pomorskie', 'Śląskie', 'Świętokrzyskie', 'Warmińsko-Mazurskie', 'Wielkopolskie', 'Zachodniopomorskie'];

  constructor(private authService: AuthenticationService, private regionservice: RegionService, private toastr: ToastrService, private router: Router) {

  }

  ngOnInit() {
    this.getRegions();
    this.initUser();
    this.regionName = '';
  }

  getRegions() {
    this.regions = this.regionservice.getRegionList();
  }

  initUser() {
    this.user = { username: null, password: null, email: null, name: null, surname: null, region: this.regionName };
  }

  registerUserAccount() {
    this.user.region = this.regionName;
    this.authService.register(this.user).subscribe(response => {
      this.router.navigate(['/home']);
    },
    (error: HttpErrorResponse) => {
      this.showErrorAlert('Sprobuj ponownie!');
      // console.log(error.error);
    });
  }

  showSuccessAlert() {
    this.toastr.success('Pomyślnie zarejestrowano. Odbierz maila w celu potwierdzenia!', 'SUKCES!');
  }

  showErrorAlert(error: string) {
    this.toastr.error(error, 'BŁĄD!');
  }

}
