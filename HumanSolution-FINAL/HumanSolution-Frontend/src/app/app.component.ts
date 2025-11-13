import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistroEvaluacionDesempenoComponent } from './components/registro-evaluacion-desempeno/registro-evaluacion-desempeno.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RegistroEvaluacionDesempenoComponent],
  template: '<app-registro-evaluacion-desempeno></app-registro-evaluacion-desempeno>',
  styleUrls: ['./app.css']
})
export class AppComponent {
  title = 'HumanSolution-Frontend';
}
