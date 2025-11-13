import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';

/**
 * Componente para el registro de usuarios
 * Implementa las validaciones Usuario-PO-01 a Usuario-PO-07
 */
@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registro-usuario.component.html',
  styleUrls: ['./registro-usuario.component.css']
})
export class RegistroUsuarioComponent implements OnInit {
  formularioRegistro: FormGroup;
  roles: any[] = [];
  rolesMap: Map<string, string> = new Map();
  mensaje: { texto: string, tipo: 'success' | 'error' } | null = null;
  cargando = false;
  mostrarContrasenia = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService
  ) {
    this.formularioRegistro = this.fb.group({
      nombre: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/)
      ]],
      apellido: ['', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/)
      ]],
      numeroDocumento: ['', [
        Validators.required,
        Validators.pattern(/^\d{5,15}$/)
      ]],
      correo: ['', [
        Validators.required,
        Validators.email
      ]],
      contrasenia: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&+=!]).{8,}$/)
      ]],
      rol: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cargarRoles();
  }

  cargarRoles(): void {
    this.usuarioService.obtenerRoles().subscribe({
      next: (response) => {
        if (response && response.data) {
          this.roles = response.data;
          this.roles.forEach(rol => {
            this.rolesMap.set(rol.id, rol.nombre);
          });
          console.log('Roles cargados:', this.roles);
        } else if (Array.isArray(response)) {
          this.roles = response;
          this.roles.forEach(rol => {
            this.rolesMap.set(rol.id, rol.nombre);
          });
        } else {
          console.warn('Formato de respuesta inesperado:', response);
          this.roles = [];
        }
      },
      error: (error) => {
        console.error('Error al cargar roles:', error);
        console.error('Status:', error.status);
        console.error('StatusText:', error.statusText);

        if (error.status === 0 || error.status === undefined) {
          console.warn('No se pudo conectar al backend. Usando roles por defecto.');
        }

        this.roles = [
          { id: '550e8400-e29b-41d4-a716-446655440000', nombre: 'Postulante' },
          { id: '550e8400-e29b-41d4-a716-446655440001', nombre: 'Empleado' },
          { id: '550e8400-e29b-41d4-a716-446655440002', nombre: 'RRHH' }
        ];
        this.roles.forEach(rol => {
          this.rolesMap.set(rol.id, rol.nombre);
        });
      }
    });
  }

  registrarUsuario(): void {
    console.log('Intentando registrar usuario...');
    console.log('Formulario válido:', this.formularioRegistro.valid);
    console.log('Valores:', this.formularioRegistro.value);

    if (this.formularioRegistro.invalid) {
      this.formularioRegistro.markAllAsTouched();
      console.log('Formulario inválido - mostrando errores');
      return;
    }

    this.cargando = true;
    this.mensaje = null;
    console.log('Valor de rol en el formulario:', this.formularioRegistro.value.rol);
    const usuarioDTO = {
      documento: this.formularioRegistro.value.numeroDocumento,
      nombre: this.formularioRegistro.value.nombre + ' ' + this.formularioRegistro.value.apellido,
      correo: this.formularioRegistro.value.correo,
      contrasena: this.formularioRegistro.value.contrasenia,
      rol: {
        id: this.formularioRegistro.value.rol
      }
    };


    console.log('Enviando al backend:', usuarioDTO);

    this.usuarioService.registrarUsuario(usuarioDTO).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);

        if (response && response.success !== false) {
          const mensaje = response.message || response.mensaje || 'Usuario registrado exitosamente';
          this.mensaje = {
            texto: '✅ ' + mensaje,
            tipo: 'success'
          };
          this.formularioRegistro.reset();
          setTimeout(() => this.mensaje = null, 5000);
        } else {
          const mensaje = response?.message || response?.mensaje || 'Error al registrar usuario';
          this.mensaje = {
            texto: '❌ ' + mensaje,
            tipo: 'error'
          };
        }

        this.cargando = false;
      },
      error: (error) => {
        console.error('Error al registrar usuario:', error);
        const mensajeError = error.error?.message || error.error?.mensaje || 'Error en la comunicación con el servidor';
        this.mensaje = {
          texto: '❌ ' + mensajeError,
          tipo: 'error'
        };
        this.cargando = false;
      }
    });
  }

  limpiarFormulario(): void {
    console.log('Limpiando formulario');
    this.formularioRegistro.reset();
    this.mensaje = null;
  }

  toggleMostrarContrasenia(): void {
    this.mostrarContrasenia = !this.mostrarContrasenia;
  }

  obtenerMensajeError(campo: string): string {
    const control = this.formularioRegistro.get(campo);

    if (control?.hasError('required')) {
      const articulo = campo === 'contrasenia' ? 'La' : 'El';
      return `${articulo} ${campo} es obligatori${campo === 'contrasenia' ? 'a' : 'o'}`;
    }

    if (control?.hasError('pattern')) {
      if (campo === 'nombre' || campo === 'apellido') {
        return 'Solo se permiten letras y espacios';
      }
      if (campo === 'numeroDocumento') {
        return 'Debe tener entre 5 y 15 dígitos';
      }
      if (campo === 'contrasenia') {
        return 'Debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!)';
      }
    }

    if (control?.hasError('email')) {
      return 'El correo no tiene un formato válido';
    }

    if (control?.hasError('minlength')) {
      const minLength = control.errors?.['minlength'].requiredLength;
      return `Debe tener al menos ${minLength} caracteres`;
    }

    return '';
  }

  esInvalido(campo: string): boolean {
    const control = this.formularioRegistro.get(campo);
    return !!(control?.invalid && (control?.dirty || control?.touched));
  }

  esValido(campo: string): boolean {
    const control = this.formularioRegistro.get(campo);
    return !!(control?.valid && control?.dirty);
  }

  cumpleMinCaracteres(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return contrasenia.length >= 8;
  }

  cumpleMayuscula(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /[A-Z]/.test(contrasenia);
  }

  cumpleMinuscula(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /[a-z]/.test(contrasenia);
  }

  cumpleNumero(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /\d/.test(contrasenia);
  }

  cumpleCaracterEspecial(): boolean {
    const contrasenia = this.formularioRegistro.get('contrasenia')?.value || '';
    return /[@#$%^&+=!]/.test(contrasenia);
  }
}
