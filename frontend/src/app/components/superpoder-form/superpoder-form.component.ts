import { Component } from '@angular/core';
import { Superpoder } from '../../models/superpoder.model';
import { SuperpoderService } from '../../services/superpoder.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-superpoder-form',
  templateUrl: './superpoder-form.component.html'
})
export class SuperpoderFormComponent {
  poder: Superpoder = { descricao: '', superpoder: '' };

  constructor(private spService: SuperpoderService, private router: Router) {}

  salvar() {
    this.spService.criar(this.poder).subscribe(() => this.router.navigate(['/superpoderes']));
  }
}
