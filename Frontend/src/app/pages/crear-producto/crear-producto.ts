import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductoService } from '../../service/producto.service';
import { Producto } from '../../interfaces/producto.interface';
import { AlertService } from '../../service/alert.service';

@Component({
  selector: 'app-crear-producto',
  imports: [ReactiveFormsModule],
  templateUrl: './crear-producto.html',
  styleUrl: './crear-producto.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CrearProducto {
  private fb = inject(FormBuilder);
  private productoService = inject(ProductoService);
  private router = inject(Router);
  private alertService = inject(AlertService);

  loading = signal(false);
  errorMsg = signal('');

  form = this.fb.group({
    nombre:       ['', Validators.required],
    descripcion:  [''],
    precio:       [null as number | null, [Validators.required, Validators.min(0)]],
    stock:        [null as number | null, [Validators.required, Validators.min(0)]],
    tipoProducto: ['', Validators.required],
  });

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading.set(true);
    this.errorMsg.set('');

    const producto = this.form.getRawValue() as Producto;

    this.productoService.create(producto).subscribe({
      next: () => {
        this.loading.set(false);
        this.alertService.show('Producto creado exitosamente.', 'success');
        this.router.navigate(['/home/productos']);
      },
      error: (err) => {
        this.loading.set(false);
        this.errorMsg.set(err?.error?.message ?? 'Error al crear el producto.');
        this.alertService.show('Error al crear el producto.', 'error');
      },
    });
  }
}
