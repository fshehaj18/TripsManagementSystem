import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Flight } from '../common/flight';
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
    let date: Date = new Date(2017, 4, 4)
    let f:Flight = {"origin":"", "destination":"", "departureDate":date, "arrivalDate":date};
      return  this.httpClient.get(`http://localhost:8080/user/trip`).pipe(
        map((res: any) => 
        
        res.map((data) => {return {
            tripId:data.tripId,
            tripReason: data.tripReason,
            origin: data.origin,
            destination: data.destination,
            departureDate: data.departureDate,
            arrivalDate: data.arrivalDate,
            tripStatus: data.tripStatus,
            flights: data.flights
          }
  })
      ));
  }
  respondTripRequest(id: number, response: any)
  {
    return this.httpClient.put(`http://localhost:8080/admin/trip/${id}/send`, response);
  }

  viewFlightsByTripId(id: number): Observable<Object>
  {
      return this.httpClient.get(`http://localhost:8080/user/trip/${id}/flights`)/*.pipe(
        map((res: any) => 
        res.map((data) => {
          return {
            origin: data.origin,
            destination: data.destination,
            departureDate: data.departureDate,
            arrivalDate: data.arrivalDate,
          }
  })
      ))*/;
}

}