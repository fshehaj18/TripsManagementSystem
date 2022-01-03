import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Trip } from 'src/app/common/trip';
import { TripService } from 'src/app/services/trip.service';
import { faArrowAltCircleDown, faArrowAltCircleLeft, faArrowAltCircleRight, faCheck, faEdit, faPlane, faPlus, faSeedling, faTimes, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Flight } from 'src/app/common/flight';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FlightService } from 'src/app/services/flight.service';
import { delay } from 'rxjs/operators';


@Component({
  selector: 'app-user-trip-list',
  templateUrl: './user-trip-list.component.html',
  styleUrls: ['./user-trip-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ height: '*', visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class UserTripListComponent implements OnInit, AfterViewInit {

  trips: Trip[] = [];
  faTimes = faTimes;
  faCheck = faCheck;
  faPlus = faPlus;
  faTrash = faTrash;
  faEdit = faEdit;
  faArrow = faArrowAltCircleRight;
  faFlight = faPlane;
  faArrowDown = faArrowAltCircleDown;

  flights: Flight[];

  displayedColumns = ['id','origin', 'destination', 'reason', 'arrivalDate', 'departureDate','status', 'edit', 'delete', 'send', 'viewFlightsButton', 'addFlightsButton'];
  displayedColumns2 = ['origin', 'destination', 'departureDate', 'arrivalDate'];
  toggle = 'collapsed';

  constructor(private tripService: TripService, private route:ActivatedRoute, private routes: Router) { }
  ngAfterViewInit(): void {
  }

  id = -1;



  ngOnInit(): void {
    
    this.listTrips();
    
  
  }
  listTrips() {
    
    this.tripService.getTripsByUser().subscribe(
      (data: Trip[]) =>{
        this.trips = data;
        console.log(this.trips)
      });
    }


     listFlights(trip: number){
      console.log(trip)
     /* let selectedTrip: Trip;
      this.tripService.getTripById(trip).subscribe(
        data=>{
          selectedTrip = data
        }
      );
      for( var trip=0; trip < this.trips.length; trip++){
        if(selectedTrip.tripId == this.trips[trip].tripId){
          this.tripService.viewFlightsByTripId(this.trips[trip].tripId).subscribe( 
            (data: Flight[]) => {
           
            console.log(data)
            this.trips[trip].flights= data
          }
          )
            break;
        }*/
      }

      /*let date: Date = new Date(2017, 4, 4)
      let f:Flight = {"origin":"", "destination":"", "departureDate":date, "arrivalDate":date};
      this.flights = [f,f,f];
      for( var trip=0; trip < this.trips.length; trip++){
       this.trips[trip].flights = [f,f,f]
        this.tripService.viewFlightsByTripId(this.trips[trip].tripId).subscribe( 
          (data: Flight[]) => {
         
          console.log(data)
          this.trips[trip].flights= data
        }
        , error => { console.log(error); }
        );*/
    

     addFlights(trip: number, f: Flight[])
    {
      this.trips[trip].flights = f;
    }

  sendTrip(id: number){
    console.log(id);
    this.tripService.sendTrip(id).subscribe(
      
    );
    window.location.reload();
  }
  editTrip(id: number){

  }
  deleteTrip(id: number){

  }

  async viewFlights(id: number)
  {

    await delay(5000)
    //this.listFlights();
    
    if(this.toggle === 'collapsed'){
    this.toggle = 'expanded';
    this.listFlights(id);
   // this.addFlights();
    }
    else{
      this.toggle = 'collapsed'
      
    }
    
   
  }

  add(id: number){
    localStorage.setItem('tripId', id.toString());
    this.routes.navigate(['/user/flights'])
  }
}
