import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { faCoffee } from '@fortawesome/free-solid-svg-icons';
import {faUserPlus} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'user-management-frontend';
 

  isButtonVisible: boolean = true;
  
  faCoffee = faCoffee;
  faUserPlus = faUserPlus;

  constructor(private router: Router){
   
  }
   
  ngOnInit(): void {
  }
  
  hasRoute(route: string) {
    return this.router.url.includes(route);
  }
}
