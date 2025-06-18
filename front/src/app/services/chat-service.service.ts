import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import * as SockJS from 'sockjs-client';
import { ChatMessage } from '../interfaces/chatMessage';

@Injectable({
  providedIn: 'root'
})

@Injectable({ providedIn: 'root' })
export class ChatService {
  private stompClient: Client;
  private messages$ = new BehaviorSubject<ChatMessage[]>([]);

  constructor() {
    this.stompClient = new Client({
      brokerURL: '',
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      reconnectDelay: 5000,
    });

    this.stompClient.onConnect = () => {
      this.stompClient.subscribe('/topic/messages', (msg: IMessage) => {
        const chatMessage = JSON.parse(msg.body);
        const current = this.messages$.value;
        this.messages$.next([...current, chatMessage]);
      });
    };

    this.stompClient.activate();
  }

  get messages() {
    return this.messages$.asObservable();
  }

  send(message: ChatMessage) {
    this.stompClient.publish({
      destination: '/app/chat.send',
      body: JSON.stringify(message),
    });
  }
}