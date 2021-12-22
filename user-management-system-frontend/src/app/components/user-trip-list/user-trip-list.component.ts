import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Trip } from 'src/app/common/trip';
import { TripService } from 'src/app/services/trip.service';
import { faArrowAltCircleLeft, faArrowAltCircleRight, faCheck, faEdit, faPlus, faSeedling, faTimes, faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user-trip-list',
  templateUrl: './user-trip-list.component.html',
  styleUrls: ['./user-trip-list.component.css']
})
export class UserTripListComponent implements OnInit {

  trips: Trip[];
  faTimes = faTimes;
  faCheck = faCheck;
  faPlus = faPlus;
  faTrash = faTrash;
  faEdit = faEdit;
  faArrow = faArrowAltCircleRight;

  constructor(private tripService: TripService, private route:ActivatedRoute, private routes: Router) { }

  id = -1;

  ngOnInit(): void {
    this.listTrips();
  }
  listTrips() {
    this.tripService.getTripsByUser().subscribe(
      (data: Trip[]) =>{
        this.trips = data;
      });
  }

  sendTrip(id: number){
    console.log(id);
    this.tripService.sendTrip(id).subscribe(
      
    );
  }
  editTrip(id: number){

  }
  deleteTrip(id: number){

  }
}
