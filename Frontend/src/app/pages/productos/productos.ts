import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ProductoService } from '../../service/producto.service';
import { Producto } from '../../interfaces/producto.interface';
import { AlertService } from '../../service/alert.service';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-productos',
  imports: [CurrencyPipe, ReactiveFormsModule],
  templateUrl: './productos.html',
  styleUrl: './productos.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Productos implements OnInit {
  private productoService = inject(ProductoService);
  private fb = inject(FormBuilder);
  private alertService = inject(AlertService);
  private loginService = inject(LoginService);

  isAdmin = this.loginService.isAdmin();

  productos = signal<Producto[]>([]);
  loading = signal(true);
  errorMsg = signal('');
  confirmDeleteId = signal<number | null>(null);

  editingProduct = signal<Producto | null>(null);
  saving = signal(false);
  editError = signal('');

  editForm = this.fb.group({
    nombre:       ['', Validators.required],
    descripcion:  [''],
    precio:       [null as number | null, [Validators.required, Validators.min(0)]],
    stock:        [null as number | null, [Validators.required, Validators.min(0)]],
    tipoProducto: ['', Validators.required],
  });

  ngOnInit(): void {
    this.loadProductos();
  }

  loadProductos(): void {
    this.loading.set(true);
    this.errorMsg.set('');
    this.productoService.getAll().subscribe({
      next: (data) => {
        this.productos.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        this.errorMsg.set(err?.error?.message ?? 'Error al cargar los productos.');
        this.loading.set(false);
      },
    });
  }

  openEdit(producto: Producto): void {
    this.editingProduct.set(producto);
    this.editError.set('');
    this.editForm.patchValue({
      nombre: producto.nombre,
      descripcion: producto.descripcion,
      precio: producto.precio,
      stock: producto.stock,
      tipoProducto: producto.tipoProducto,
    });
  }

  cancelEdit(): void {
    this.editingProduct.set(null);
  }

  saveEdit(): void {
    if (this.editForm.invalid) {
      this.editForm.markAllAsTouched();
      return;
    }
    const product = this.editingProduct();
    if (!product?.id) return;

    this.saving.set(true);
    this.editError.set('');

    this.productoService.update(product.id, this.editForm.getRawValue() as Producto).subscribe({
      next: () => {
        this.saving.set(false);
        this.editingProduct.set(null);
        this.alertService.show('Producto actualizado exitosamente.', 'success');
        this.loadProductos();
      },
      error: (err) => {
        this.saving.set(false);
        this.editError.set(err?.error?.message ?? 'Error al actualizar el producto.');
        this.alertService.show('Error al actualizar el producto.', 'error');
      },
    });
  }

  askDelete(id: number): void {
    this.confirmDeleteId.set(id);
  }

  cancelDelete(): void {
    this.confirmDeleteId.set(null);
  }

  confirmDelete(): void {
    const id = this.confirmDeleteId();
    if (id === null) return;
    this.confirmDeleteId.set(null);
    this.productoService.delete(id).subscribe({
      next: () => {
        this.alertService.show('Producto eliminado exitosamente.', 'success');
        this.loadProductos();
      },
      error: (err) => {
        this.errorMsg.set(err?.error?.message ?? 'Error al eliminar el producto.');
        this.alertService.show('Error al eliminar el producto.', 'error');
      },
    });
  }
}
