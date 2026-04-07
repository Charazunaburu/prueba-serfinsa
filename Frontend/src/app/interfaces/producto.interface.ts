export interface Producto {
  id?: number;
  nombre: string;
  descripcion: string;
  precio: number;
  stock: number;
  tipoProducto: string;
  createAt?: string;
  createdBy?: number;
  updateAt?: string;
  updateBy?: number;
}

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp: string;
}
