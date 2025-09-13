import { Component, OnInit } from '@angular/core';
import { Heroi } from '../../models/heroi.model';
import { HeroiService } from '../../services/heroi.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-herois-list',
  templateUrl: './herois-list.component.html'
})
export class HeroisListComponent implements OnInit {
  herois: Heroi[] = [];

  constructor(private heroiService: HeroiService, private router: Router) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar() {
    this.heroiService.listar().subscribe(data => this.herois = data);
  }

  deletar(id?: number) {
    if (!id) return;
    if (confirm('Confirma exclusão?')) {
      this.heroiService.deletar(id).subscribe(() => this.carregar());
    }
  }
}
