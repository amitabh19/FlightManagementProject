export class ScheduledFlight {

    flight:{
        flightNumber:Number,
        carrierName:String,
        flightModel:String,
        seatCapacity:String
    };
    availableSeats:Number;
    schedule:{
        scheduleId:Number,
        sourceAirport:{
            airportCode:String,
            airportName:String,
            airportLocation:String
        },
        destinationAirport:{
            airportCode:String,
            airportName:String,
            airportLocation:String
        },
        arrival:Date,
        departureTime:Date
    }

}
