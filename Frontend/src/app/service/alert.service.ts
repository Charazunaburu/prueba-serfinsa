import { Injectable, signal } from '@angular/core';

export type AlertType = 'success' | 'error' | 'info';

export interface Alert {
  message: string;
  type: AlertType;
}

@Injectable({ providedIn: 'root' })
export class AlertService {
  alert = signal<Alert | null>(null);

  show(message: string, type: AlertType = 'success', duration = 3500): void {
    this.alert.set({ message, type });
    setTimeout(() => this.dismiss(), duration);
  }

  dismiss(): void {
    this.alert.set(null);
  }
}
