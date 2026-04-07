import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AlertService } from './service/alert.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Frontend');
  protected alertService = inject(AlertService);
}
