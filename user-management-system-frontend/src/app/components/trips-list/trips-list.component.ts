import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faCheck, faEdit, faTimes, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Trip } from 'src/app/common/trip';
import { TripService } from 'src/app/services/trip.service';

@Component({
  selector: 'app-trips-list',
  templateUrl: './trips-list.component.html',
  styleUrls: ['./trips-list.component.css']
})
export class TripsListComponent implements OnInit {

  trips: Trip[];

  constructor(private tripService: TripService, private route:ActivatedRoute, private router: Router) { }

  
  faCheck = faCheck;
  faTimes = faTimes;

  ngOnInit(): void {
    this.listTrips();
  }
  listTrips() {
    this.tripService.getTrips().subscribe(
    (data: Trip[]) =>{
      this.trips = data;
     
    });
  }

  accept(id: number)
  {
    var tripStatusDto = {"tripStatus":"ACCEPTED"};
    this.tripService.respondTripRequest(id, tripStatusDto).subscribe();
    this.router.navigate(['/admin/trips'])
    .then(() => {
      window.location.reload();
   });;
  }

  reject(id: number)
  {
    var tripStatusDto = {"tripStatus":"REJECTED"};
    this.tripService.respondTripRequest(id, tripStatusDto).subscribe();
    this.router.navigate(['/admin/trips'])
    .then(() => {
      window.location.reload();
   });;
  }

   
}
