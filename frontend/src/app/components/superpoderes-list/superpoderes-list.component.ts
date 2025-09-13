import { Component, OnInit } from '@angular/core';
import { Superpoder } from '../../models/superpoder.model';
import { SuperpoderService } from '../../services/superpoder.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-superpoderes-list',
  templateUrl: './superpoderes-list.component.html'
})
export class SuperpoderesListComponent implements OnInit {
  poderes: Superpoder[] = [];

  constructor(private spService: SuperpoderService, private router: Router) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar() {
    this.spService.listar().subscribe(data => this.poderes = data);
  }
}
