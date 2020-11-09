import { ScheduledFlight } from "./scheduled-flight";
import { User } from "./user";

export class Booking {
    bookingId:Number;
    userId:User;
    bookingDateTime:Date;
    ticketCost:Number;
    scheduledFlight:ScheduledFlight;
    noOfPassengers:Number;
    passenger:[{
        passengerId:Number,
        passengerName:String,
        pnrNumber:String,
        age:Number,
        luggage:Number
    }]    
}
