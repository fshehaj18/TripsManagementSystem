import { Component, OnInit } from '@angular/core';
import { Trip } from 'src/app/common/trip';
import { TripService } from 'src/app/services/trip.service';

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.css']
})
export class CreateTripComponent implements OnInit {

  selected='MEETING';
  trip: Trip = new Trip();
  constructor(private tripService: TripService) { }

  ngOnInit(): void {
  }
  onSubmit(){
    this.saveTrip();
  }
  saveTrip() {
    this.tripService.createTrip(this.trip).subscribe( data => {

      console.log(data);
    },
  
    );
  }
  
}
