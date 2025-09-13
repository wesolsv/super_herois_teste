import { Component, OnInit } from '@angular/core';
import { Heroi } from '../../models/heroi.model';
import { Superpoder } from '../../models/superpoder.model';
import { HeroiService } from '../../services/heroi.service';
import { SuperpoderService } from '../../services/superpoder.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-heroi-form',
  templateUrl: './heroi-form.component.html'
})
export class HeroiFormComponent implements OnInit {
  heroi: Heroi = { nome: '', nomeHeroi: '', dataNascimento: '', altura: 0, peso: 0, superpoderes: [] };
  superpoderes: Superpoder[] = [];
  isEdicao = false;
  id?: number;

  constructor(
    private heroiService: HeroiService,
    private superpoderService: SuperpoderService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.superpoderService.listar().subscribe(data => this.superpoderes = data);
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.isEdicao = true;
      this.heroiService.buscarPorId(this.id).subscribe(data => this.heroi = data);
    }
  }

  salvar() {
    if (this.isEdicao && this.id) {
      this.heroiService.atualizar(this.id, this.heroi).subscribe(() => this.router.navigate(['/herois']));
    } else {
      this.heroiService.criar(this.heroi).subscribe(() => this.router.navigate(['/herois']));
    }
  }
}
