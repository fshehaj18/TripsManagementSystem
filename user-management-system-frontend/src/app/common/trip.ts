import { Flight } from "./flight";

export class Trip {

    tripId!: number;
    tripStatus!: string;
    description!: string;
    origin!: string;
    destination!: string;
    departureDate: Date;
    arrivalDate: Date;
    tripReason!:string;
    userId!: number;
    flights!: Flight[] ;


    constructor(f: undefined|Flight){
    }

    public check(f: Flight[]){
        this.flights = f;
    }
   
}

