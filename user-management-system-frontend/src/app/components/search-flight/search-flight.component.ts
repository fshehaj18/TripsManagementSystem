import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from 'src/app/common/flight';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-search-flight',
  templateUrl: './search-flight.component.html',
  styleUrls: ['./search-flight.component.css']
})
export class SearchFlightComponent implements OnInit {
  flight:Flight = new Flight();

  flights: Flight[];

  constructor(private flightService: FlightService, private route: ActivatedRoute, private routes: Router) { }

  ngOnInit(): void {
    this.flights = [];
  }

  onSubmit()
  {
    this.flightService.searchFlights(this.flight).subscribe(
      (data:Flight[]) =>{
        this.flights = data;
        
      }
    )
  }

}
