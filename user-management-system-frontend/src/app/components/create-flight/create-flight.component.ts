import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from 'src/app/common/flight';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-create-flight',
  templateUrl: './create-flight.component.html',
  styleUrls: ['./create-flight.component.css']
})
export class CreateFlightComponent implements OnInit {

  constructor(private flightService: FlightService, private router: Router) { }

  flight:Flight = new Flight();
  ngOnInit(): void {
  }

  onSubmit(){
    this.saveFlight();
  }
  saveFlight() {
    this.flightService.createFlight(this.flight).subscribe( data => {

      console.log(data);
    },
  
    );
  }

}
