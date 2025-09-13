import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" routerLink="/">Heróis CRUD</a>
      <div>
        <a class="nav-link d-inline text-white me-2" routerLink="/herois">Heróis</a>
        <a class="nav-link d-inline text-white" routerLink="/superpoderes">Superpoderes</a>
      </div>
    </div>
  </nav>
  <div class="container container-app mt-4">
    <router-outlet></router-outlet>
  </div>
  `
})
export class AppComponent {}
