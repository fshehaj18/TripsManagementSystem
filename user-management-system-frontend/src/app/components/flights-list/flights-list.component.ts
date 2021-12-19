import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Flight } from 'src/app/common/flight';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-flights-list',
  templateUrl: './flights-list.component.html',
  styleUrls: ['./flights-list.component.css']
})
export class FlightsListComponent implements OnInit {

  flights:Flight[];

  constructor(private flightService: FlightService, private route: ActivatedRoute, private routes: Router) { }

  faEdit = faEdit;
  faTrash = faTrash;

  ngOnInit(): void {
    this.listTrips();
  }
  listTrips() {
    this.flightService.getFlights().subscribe(
    (data: Flight[]) =>{
      this.flights = data;
     
    });
  }

}
