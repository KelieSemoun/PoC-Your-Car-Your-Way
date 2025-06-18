import { Component, OnInit } from '@angular/core';
import { ChatMessage } from 'src/app/interfaces/chatMessage';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  message = '';
  messages: ChatMessage[] = [];

  constructor(private chatService: ChatService) {
    this.chatService.messages.subscribe(msgs => this.messages = msgs);
  }

  sendMessage() {
    if (!this.message.trim()) return;
    this.chatService.send({ sender: 'Moi', content: this.message });
    this.message = '';
  }
}
