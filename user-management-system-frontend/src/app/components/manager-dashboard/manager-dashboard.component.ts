import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faCoffee, faUserPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css']
})
export class ManagerDashboardComponent implements OnInit {

  isButtonVisible: boolean = true;
  
  faUserPlus = faUserPlus;
  
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  createUser()
  {
    this.router.navigate(['create-user']);  }

  hasRoute(route: string) {
    return this.router.url.includes(route);
  }
}
