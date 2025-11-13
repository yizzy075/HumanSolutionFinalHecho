/**
 * Modelo para la Evaluación de Desempeño
 * Basado en el modelo enriquecido del documento
 */

export interface Usuario {
  id: string;
  nombre?: string;
  correo?: string;
}

export interface EvaluacionDesempeno {
  id?: string;
  usuario: Usuario;
  fecha: string;  // Formato: YYYY-MM-DD
  calificacion: number;  // Rango: 1-10
  observacion: string;  // Longitud: 5-500 caracteres
}

export interface EvaluacionDesempenoResponse {
  datos: EvaluacionDesempeno[];
  mensajes: string[];
}
