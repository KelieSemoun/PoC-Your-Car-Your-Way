import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  loginAs(username: string, password: string) {
    this.auth.login(username, password).subscribe({
      next: () => this.router.navigate(['/chat']),
      error: () => this.error = 'Erreur d’authentification'
    });
  }
}
