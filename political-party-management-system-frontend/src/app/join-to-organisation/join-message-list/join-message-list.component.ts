import { Component, OnInit } from '@angular/core';
import { JoinMessage } from '../../model/JoinMessage';
import { Observable } from 'rxjs';
import { JoinMessageService } from '../../services/join-message.service';

@Component({
  selector: 'app-join-message-list',
  templateUrl: './join-message-list.component.html',
  styleUrls: ['./join-message-list.component.css']
})
export class JoinMessageListComponent implements OnInit {

  joinMessages: Observable<JoinMessage[]>;

  constructor(private joinMessageService: JoinMessageService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.joinMessages = this.joinMessageService.getJoinMessageList();
  }
}
