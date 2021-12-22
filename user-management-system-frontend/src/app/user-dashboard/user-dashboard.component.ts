import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faMapMarker, faMapMarkerAlt, faPlus, faUserPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  faPlus = faPlus;
  faMapMarker = faMapMarkerAlt;
  
  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  createTrip(){
    this.router.navigate(['/trips/create-trip'])
  }

}
