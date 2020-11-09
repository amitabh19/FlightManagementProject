import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user';
import {ScheduledFlight} from '../scheduled-flight';
import { DatePipe } from '@angular/common';
import {Router} from '@angular/router';
@Component({
  selector: 'app-search-list',
  templateUrl: './search-list.component.html',
  styleUrls: ['./search-list.component.css']
})
export class SearchListComponent implements OnInit {

  flightList:ScheduledFlight[];
  scheduledFlight:ScheduledFlight;
  constructor(private _userService:UserService ,public datepipe: DatePipe,private _router:Router) { }

  ngOnInit() {
   this.flightList= this._userService.getScheduledFlight();
  console.log("the list of flight is:"+this.flightList.length);
  }

  createBookingPage(scheduledFlight){
      this.scheduledFlight = scheduledFlight;
      this._userService.setScheduledFlightObj(scheduledFlight);
      this._router.navigate(['/createBooking']);
  }

}
