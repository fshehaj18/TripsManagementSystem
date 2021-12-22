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
      let getTripsUrl =  `http://localhost:8080/admin/trip/`;

    return this.httpClient.get(`${getTripsUrl}`);
  }

  sendTrip(id: number): Observable<Object>
  {
    console.log(id);
    return this.httpClient.put(`http://localhost:8080/user/trip/${id}/send/`, null);
    
  }
  getTripsByUser(): Observable<Object>{
      return  this.httpClient.get(`http://localhost:8080/user/trip`);
  }
  respondTripRequest(id: number, response: any)
  {
    return this.httpClient.put(`http://localhost:8080/admin/trip/${id}/send`, response);
  }
}
