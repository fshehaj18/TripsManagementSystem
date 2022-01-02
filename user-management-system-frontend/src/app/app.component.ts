import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { faCoffee } from '@fortawesome/free-solid-svg-icons';
import {faUserPlus} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from './services/auth.service';

  
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'trips-management-system-frontend';
 

  isButtonVisible: boolean = true;
  
  faCoffee = faCoffee;
  faUserPlus = faUserPlus;

  constructor(private router: Router, private authService: AuthService){
   
  }
   
  ngOnInit(): void {
  }
  
  hasRoute(route: string) {
    return this.router.url.includes(route);
  }

  goToDashBoard() {
    let role = this.authService.getRole();
    if (role === 'ROLE_ADMIN')
      this.router.navigate(['admin']);
    if (role === 'ROLE_USER')
      this.router.navigate(['/user']);

  }
}
