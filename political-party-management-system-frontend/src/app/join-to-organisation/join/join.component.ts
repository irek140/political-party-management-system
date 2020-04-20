import { Component, OnInit } from '@angular/core';
import { JoinService } from '../../services/join.service';
import { ToastrService } from 'ngx-toastr';
import { JoinMessage } from '../../model/JoinMessage';
import { User } from '../../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css']
})
export class JoinComponent implements OnInit {

  contentMessage: string;
  hasJoinMessage = false;
  me: User;

  constructor(private joinService: JoinService, private toastService: ToastrService, private router: Router) { }

  ngOnInit() {
    this.contentMessage = '';
    this.hasJoinMessageMethod();
    this.showSendJoinMessageAlert();
  }

  join() {
    this.joinService.join( { content: this.contentMessage} ).subscribe(
      data => {
        console.log(data);
        this.contentMessage = '';
        this.showSuccessAlert();
        this.router.navigate(['/user-panel']);
      },
      error => console.log(error));
  }

  showSuccessAlert() {
    this.toastService.success('Prośba o członkostwo została wysłana!', 'SUKCES!');
  }

  hasJoinMessageMethod() {
    this.joinService.getJoinMessageInfo().subscribe(
      data => {
        console.log(data);
        this.me = data;
        this.hasJoinMessage = this.me.sendRequest;
      },
      error => console.log(error)
    );
  }

  showSendJoinMessageAlert() {
    if (this.hasJoinMessage) {
      this.toastService.success('Wysłałeś/łaś już prośbę o członkostwo!', 'Komunikat!');
    }
  }

}
