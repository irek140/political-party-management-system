import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageService } from 'src/app/services/message.service';
import { Message } from 'src/app/model/Message';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {

  messages: Observable<Message[]>;

  constructor(private messageService: MessageService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.messages = this.messageService.getMessagesList();
  }
}
