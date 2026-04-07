import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../service/login.service';

export const adminGuard: CanActivateFn = () => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  if (loginService.isAdmin()) {
    return true;
  }

  return router.createUrlTree(['/home/productos']);
};
