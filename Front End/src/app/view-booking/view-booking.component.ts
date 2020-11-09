import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user';
import {ScheduledFlight} from '../scheduled-flight';
import { DatePipe } from '@angular/common';
import {Router} from '@angular/router';
import {Booking} from '../booking';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {

  bookingList:Booking[];
  user:User;
  constructor(private _userService:UserService ,public datepipe: DatePipe,private _router:Router) { }

  ngOnInit() {

    this.user = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.user);
    if(this.user==null)
    {
      this._router.navigate(['login']);
    }
    console.log(this.user);
    this._userService.getBookingsById(this.user.userId).subscribe( (bookings)=>{
      this.bookingList = bookings;
      console.log(this.bookingList);
    } )

  }

}
