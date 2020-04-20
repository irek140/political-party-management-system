import { Component, OnInit, Input } from '@angular/core';
import { JoinMessage } from '../../model/JoinMessage';
import { JoinMessageService } from '../../services/join-message.service';
import { JoinMessageListComponent } from '../join-message-list/join-message-list.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-join-message-details',
  templateUrl: './join-message-details.component.html',
  styleUrls: ['./join-message-details.component.css']
})
export class JoinMessageDetailsComponent implements OnInit {

  @Input()
  joinMessage: JoinMessage;

  hiddenDiv: boolean;

  constructor(private joinMessageService: JoinMessageService, private joinMessageListComponent: JoinMessageListComponent, private router: Router) { }

  ngOnInit() {
    this.hiddenDiv = false;
  }

  accept() {
    console.log(this.joinMessage);
    this.hiddenDiv = true;
    this.joinMessageService.becomeMember(this.joinMessage.id, this.joinMessage).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
  }

  remove() {
    this.hiddenDiv = true;
    this.joinMessageService.delete(this.joinMessage.id).subscribe(
      data => {
        console.log(data);
      },
      error => console.log(error));
  }

}
