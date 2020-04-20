import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loginStatus: boolean;
  isUser: boolean;
  isAdmin: boolean;
  isMember: boolean;
  isPresident: boolean;
  isVicePresident: boolean;
  isSecretary: boolean;
  isRegionPresident: boolean;
  isRegionVicePresident: boolean;
  isRegionSecretary: boolean;
  isConstituencyPresident: boolean;
  isConstituencyVicePresident: boolean;
  isConstituencySecretary: boolean;
  name: string;
  surname: string;

  constructor(private userService: UserService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.getMe();
    this.changeValuesInMenu();
    if (this.isUser && this.isAdmin) { this.router.navigate(['/admin-panel']); }
    if (this.isPresident && this.isUser && this.isMember && !this.isRegionPresident) { this.router.navigate(['/president-panel']); }
    if (this.isVicePresident) { this.router.navigate(['/vice-president-panel']); }
    if (this.isSecretary && this.isUser && this.isMember) { this.router.navigate(['/secretary-panel']); }
    if (this.isRegionPresident) { this.router.navigate(['/region-president-panel']); }
    if (this.isRegionVicePresident) { this.router.navigate(['/region-vice-president-panel']); }
    if (this.isRegionSecretary) { this.router.navigate(['/region-secretary-panel']); }
    if (this.isConstituencyPresident && this.isUser && this.isMember) { this.router.navigate(['/constituency-president-panel']); }
    if (this.isConstituencyVicePresident) { this.router.navigate(['/constituency-vice-president-panel']); }
    if (this.isConstituencySecretary && this.isUser && this.isMember) { this.router.navigate(['/constituency-secretary-panel']); }
    if (this.isUser && this.isMember && !this.isPresident && !this.isAdmin && !this.isVicePresident && !this.isSecretary && !this.isRegionPresident && !this.isRegionVicePresident && !this.isConstituencyPresident && !this.isConstituencyVicePresident && !this.isRegionSecretary && !this.isConstituencySecretary) { this.router.navigate(['/member-panel']); }
    if (this.isUser && !this.isAdmin && !this.isMember && !this.isPresident && !this.isVicePresident && !this.isSecretary) { this.router.navigate(['/user-panel']); }
  }

  changeValuesInMenu() {
    if (this.authService.isLoggedUser()) {
      this.isUser = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedAdmin()) {
      this.isAdmin = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedPresident()) {
      this.isPresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedVicePresident()) {
      this.isVicePresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedSecretary()) {
      this.isSecretary = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedRegionPresident()) {
      this.isRegionPresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedRegionVicePresident()) {
      this.isRegionVicePresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedRegionSecretary()) {
      this.isRegionSecretary = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedConstituencyPresident()) {
      this.isConstituencyPresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedConstituencyVicePresident()) {
      this.isConstituencyVicePresident = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedConstituencySecretary()) {
      this.isConstituencySecretary = true;
      this.loginStatus = true;
    }
    if (this.authService.isLoggedMember()) {
      this.isMember = true;
      this.loginStatus = true;
    }
    this.loginStatus = true;
    if (!this.authService.isLoggedIn()) {
      this.setMenuStatusFalse();
    }
    console.log('loginStatus: ' + this.loginStatus + '; isUser: ' + this.isUser +
     '; isAdmin: ' + this.isAdmin + '; isMember: ' + this.isMember);
  }

  setMenuStatusFalse() {
    this.isUser = false;
    this.isAdmin = false;
    this.isPresident = false;
    this.isVicePresident = false;
    this.isSecretary = false;
    this.isRegionPresident = false;
    this.isRegionVicePresident = false;
    this.isRegionSecretary = false;
    this.isConstituencyPresident = false;
    this.isConstituencyVicePresident = false;
    this.isConstituencySecretary = false;
    this.isMember = false;
    this.loginStatus = false;
  }

  logout() {
    this.authService.logout();
    this.changeValuesInMenu();
    this.router.navigate(['/home']);
  }

  getMe() {
    this.userService.getMe().subscribe(
      user => {
      this.name = user.name;
      this.surname = user.surname;
      }, error => console.log(error)
      );
  }

}
