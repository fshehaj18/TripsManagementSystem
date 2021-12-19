import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Trip } from '../common/trip';

@Injectable({
  providedIn: 'root'
})
export class TripService {


  getTripById(id: number) {
    return this.httpClient.get<Trip>(`http://localhost:8080/admin/trips/${id}`);
  }

  constructor(private httpClient: HttpClient) { }

  createTrip(trip: Trip): Observable<Object>
  {

    let addUrl=`http://localhost:8080/user/trip`;

      return this.httpClient.post(`${addUrl}`, trip);
   
  }

  getTrips(): Observable<Object>
  
  {
      let getTripsUrl =  `http://localhost:8080/admin/trip`;

    return this.httpClient.get(`${getTripsUrl}`);
  }

  respondTripRequest(id: number)
  {
    let getTripsUrl =  `http://localhost:8080/admin/trip/${id}/send`;

    return this.httpClient.put(`${getTripsUrl}`, id);
  }
}
