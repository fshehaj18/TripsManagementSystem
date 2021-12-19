import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Trip } from 'src/app/common/trip';
import { TripService } from 'src/app/services/trip.service';

@Component({
  selector: 'app-edit-trip',
  templateUrl: './edit-trip.component.html',
  styleUrls: ['./edit-trip.component.css']
})
export class EditTripComponent implements OnInit {

  trip:Trip = new Trip();
  id = -1;

  constructor(private tripService: TripService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.tripService.getTripById(this.id).subscribe(data => {
      this.trip = data;
    }, error => console.log(error));
  }
  

  onSubmit(){

  }
}
