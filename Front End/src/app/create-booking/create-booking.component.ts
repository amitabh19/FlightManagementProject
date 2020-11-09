import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
import { ScheduledFlight } from '../scheduled-flight';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { Booking } from '../booking';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent implements OnInit {

  bookingObject: Booking;
  scheduledFlight: ScheduledFlight;
  user: User;
  bookingDone: Boolean = true;
  passengerAdded: Boolean = true;
  passengers: [
    {
      passengerId: Number,
      passengerName: String,
      pnrNumber: String,
      age: Number,
      luggage: Number
    }
  ] = [{
    passengerId: null,
    passengerName: "",
    pnrNumber: null,
    age: null,
    luggage: null
  }
    ]
     uins:string[]=[];
  constructor(private _userService: UserService, public datepipe: DatePipe, private _router: Router) { }

  ngOnInit() {
    console.log(this._userService.getScheduledFlightObj());
    this.scheduledFlight = this._userService.getScheduledFlightObj();
    this.user = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.user);
    if (this.user == null) {
      this._router.navigate(['login']);
    }
  }

  addPassenger(name, age, luggage,uin) {
    //this.uin.push(uin);
    var flag =1;
    if(this.uins.length==0){
      this.uins.push(uin);
      let s = new String("53123")
    
    let passenger = {
      passengerId: undefined,
      passengerName: name,
      pnrNumber: s,
      age: age,
      luggage: luggage
    };

    this.passengers.push(passenger);

    if (this.passengers[0].passengerName == "") {
      this.passengers.splice(0, 1);
    }
    console.log(this.passengers);
    this.passengerAdded = false;
    }

    else{
    for(var i=0 ;i< this.uins.length;i++){
      console.log("inside loop")
      console.log(this.uins[i]);
      if(uin===this.uins[i]){
        flag=0;
        alert("Passenger with this UIN already exists")
        console.log("already exists");
        break;
      }
    }

    if(flag){
      this.uins.push(uin);
      let s = new String("53123")
    
    let passenger = {
      passengerId: undefined,
      passengerName: name,
      pnrNumber: s,
      age: age,
      luggage: luggage
    };

    this.passengers.push(passenger);

    if (this.passengers[0].passengerName == "") {
      this.passengers.splice(0, 1);
    }
    console.log(this.passengers);
    this.passengerAdded = false;
    }

     }
   console.log("List of passengers before booking"+this.passengers); 
  }

  createBooking() {

    this.bookingObject = {
      "bookingId": undefined,
      "userId": this.user,
      "bookingDateTime": new Date(),
      "ticketCost": 500,
      "scheduledFlight": this.scheduledFlight,
      "noOfPassengers": this.passengers.length,
      "passenger": this.passengers
    }
    console.log("booking object is: " + this.bookingObject);

    this._userService.makeBooking(this.bookingObject).subscribe((booking) => {
      console.log(booking);
    })
    this.bookingDone = false;
    alert("Booking has been successfully created. Redirecting to booking list.");
    this._router.navigate(['/viewBookings']);

  }

  closePassengerSuccess() {
    this.passengerAdded = true;
  }
}
