import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './home.html',
  styleUrl: './home.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Home {
  private loginService = inject(LoginService);
  private router = inject(Router);

  get isAdmin(): boolean {
    return this.loginService.isAdmin();
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
