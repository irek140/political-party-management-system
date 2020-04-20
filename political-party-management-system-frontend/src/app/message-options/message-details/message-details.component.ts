import { Component, OnInit, Input } from '@angular/core';
import { MessageService } from 'src/app/services/message.service';
import { MessageListComponent } from '../message-list/message-list.component';
import { Message } from 'src/app/model/Message';


@Component({
  selector: 'app-message-details',
  templateUrl: './message-details.component.html',
  styleUrls: ['./message-details.component.css']
})
export class MessageDetailsComponent implements OnInit {

  @Input()
  message: Message;

  hiddenDiv: boolean;

  constructor(private messageService: MessageService, private messageList: MessageListComponent) { }

  ngOnInit() {
    this.hiddenDiv = false;
  }

  removeMessage() {
    this.hiddenDiv = true;
    this.messageService.deleteMessage(this.message.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }


}
