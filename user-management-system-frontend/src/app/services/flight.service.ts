import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from '../common/flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  flight: Flight = new Flight;

  constructor(private httpClient: HttpClient) { }

  getFlights(): Observable<Object>
  {
      let getTripsUrl =  `http://localhost:8080/admin/flights`;

    return this.httpClient.get(`${getTripsUrl}`);
  }

  createFlight(flight: Flight): Observable<any>
  {
    let addUrl=`http://localhost:8080/admin/flight`;

    return this.httpClient.post(`${addUrl}`, flight);
  }
}
