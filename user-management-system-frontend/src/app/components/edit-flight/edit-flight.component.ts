import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from 'src/app/common/flight';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-edit-flight',
  templateUrl: './edit-flight.component.html',
  styleUrls: ['./edit-flight.component.css']
})
export class EditFlightComponent implements OnInit {

  constructor(flightService: FlightService, private route: ActivatedRoute, private router: Router) { }

  flight: Flight = new Flight();

  ngOnInit(): void {
  }

  onSubmit(){

  }

}
