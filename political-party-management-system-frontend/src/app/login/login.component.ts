import { Component, OnInit,  Output, EventEmitter } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output()
  emitStatusAfterLogin = new EventEmitter<any>();

  user: User;

  ngOnInit() {
    console.log('init');
    this.initUser();
  }

  constructor(private authService: AuthenticationService, private toastService: ToastrService, private router: Router,
     private navbar: NavbarComponent) {
  }

  initUser() {
    this.user = {
      username: null,
      password: null
    };
  }

  loginUser() {
    this.authService.login(this.user).subscribe(() => {
      this.showSuccessAlert();
      this.navbar.changeValuesInMenu();
      this.authService.reloadPage();
    },
    error => {
      this.showErrorAlert(error.error);
    },
  () => this.emitLoginStatus());
  }

  emitLoginStatus() {
    this.emitStatusAfterLogin.emit();
  }

  showSuccessAlert() {
    this.toastService.success('Pomyślnie zalogowano!', 'SUKCES!');
  }

  showErrorAlert(error: string) {
    this.toastService.error(error, 'BŁĄD!');
  }

}
