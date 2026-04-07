import { Routes } from '@angular/router';
import { authGuardGuard } from './guard/AuthGuard.guard';
import { adminGuard } from './guard/admin.guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login').then(m => m.Login),
  },
  {
    path: 'home',
    canActivate: [authGuardGuard],
    loadComponent: () => import('./pages/home/home').then(m => m.Home),
    children: [
      { path: '', redirectTo: 'productos', pathMatch: 'full' },
      {
        path: 'crear-producto',
        canActivate: [adminGuard],
        loadComponent: () =>
          import('./pages/crear-producto/crear-producto').then(m => m.CrearProducto),
      },
      {
        path: 'productos',
        loadComponent: () =>
          import('./pages/productos/productos').then(m => m.Productos),
      },
      {
        path: 'about',
        loadComponent: () =>
          import('./pages/about/about').then(m => m.About),
      },
    ],
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: 'login',
  }
];
