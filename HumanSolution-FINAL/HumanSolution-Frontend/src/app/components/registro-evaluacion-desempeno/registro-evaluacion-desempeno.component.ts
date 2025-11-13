import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { EvaluacionDesempenoService, EvaluacionDesempeno } from '../../services/evaluacion-desempeno.service';
import { UsuarioService, Usuario } from '../../services/usuario.service';

@Component({
  selector: 'app-registro-evaluacion-desempeno',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registro-evaluacion-desempeno.component.html',
  styleUrls: ['./registro-evaluacion-desempeno.component.css']
})
export class RegistroEvaluacionDesempenoComponent implements OnInit {
  evaluacionForm!: FormGroup;
  usuarios: Usuario[] = [];
  errorMessage: string = '';
  successMessage: string = '';
  isSubmitting: boolean = false;

  constructor(
    private fb: FormBuilder,
    private evaluacionService: EvaluacionDesempenoService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadUsuarios();
  }

  initForm(): void {
    const today = new Date().toISOString().split('T')[0];

    this.evaluacionForm = this.fb.group({
      idUsuario: ['', Validators.required],
      fecha: [today, Validators.required],
      calificacion: ['', [Validators.required, Validators.min(1), Validators.max(10)]],
      observacion: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(500)]],
      criterios: ['']
    });
  }

  loadUsuarios(): void {
    this.usuarioService.list().subscribe({
      next: (data: Usuario[]) => {
        this.usuarios = data;
      },
      error: (error: any) => {
        this.errorMessage = 'Error al cargar la lista de usuarios';
        console.error('Error loading users:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.evaluacionForm.valid) {
      this.isSubmitting = true;
      this.errorMessage = '';
      this.successMessage = '';

      const evaluacionData: EvaluacionDesempeno = {
        usuario: { id: this.evaluacionForm.value.idUsuario },
        fecha: this.evaluacionForm.value.fecha,
        calificacion: this.evaluacionForm.value.calificacion,
        observacion: this.evaluacionForm.value.observacion,
        criterios: this.evaluacionForm.value.criterios || ''
      };

      this.evaluacionService.create(evaluacionData).subscribe({
        next: (response: any) => {
          this.successMessage = '✅ Evaluación registrada exitosamente';
          this.isSubmitting = false;
          setTimeout(() => {
            this.onReset();
          }, 2000);
        },
        error: (error: any) => {
          this.errorMessage = error.error?.error || 'Error al registrar la evaluación';
          this.isSubmitting = false;
          console.error('Error creating evaluation:', error);
        }
      });
    }
  }

  onReset(): void {
    this.evaluacionForm.reset();
    this.errorMessage = '';
    this.successMessage = '';
    const today = new Date().toISOString().split('T')[0];
    this.evaluacionForm.patchValue({ fecha: today });
  }
}
