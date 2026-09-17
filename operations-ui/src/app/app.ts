import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

interface Message {
  role: 'user' | 'assistant';
  content: string;
}

interface ChatResponse {
  response: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  inputMessage = '';
  loading = false;
  messages: Message[] = [];

  private readonly apiUrl = 'http://localhost:8082/chat';

  constructor(
    private http: HttpClient,
    private changeDetectorRef: ChangeDetectorRef,
  ) {}

  sendMessage(message?: string): void {
    const text = (message ?? this.inputMessage).trim();

    if (!text || this.loading) {
      return;
    }

    this.messages.push({
      role: 'user',
      content: text,
    });

    this.inputMessage = '';
    this.loading = true;

    this.http.post<ChatResponse>(this.apiUrl, { message: text }).subscribe({
      next: (result) => {
        this.messages.push({
          role: 'assistant',
          content: result.response,
        });

        this.loading = false;
        this.changeDetectorRef.detectChanges();
      },

      error: (error) => {
        console.error('Chat request failed:', error);

        this.messages.push({
          role: 'assistant',
          content: 'Unable to reach the Operations AI Agent.',
        });

        this.loading = false;
        this.changeDetectorRef.detectChanges();
      },
    });
  }
}
