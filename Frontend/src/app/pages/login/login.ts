import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';
import { LoginService } from '../../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Login {
  private formBuilder = inject(FormBuilder);
  private loginService = inject(LoginService);
  private router = inject(Router);

  public loading = signal<boolean>(false);
  public errorMessage = signal<string>('');
  public successMessage = signal<string>('');

  public form = this.formBuilder.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(4)]],
  });

  public onSubmit(): void {
    this.errorMessage.set('');
    this.successMessage.set('');

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const username = this.form.controls.username.value ?? '';
    const password = this.form.controls.password.value ?? '';

    this.loading.set(true);
    this.loginService.login(username, password)
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: (response: any) => {
          const token = response?.accessToken ?? '';
          localStorage.setItem('token', token);
          this.router.navigate(['/home']);
        },
        error: () => {
          this.errorMessage.set('Usuario o contraseña inválidos.');
        }
      });
  }

}
