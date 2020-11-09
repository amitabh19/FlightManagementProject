import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user';
import {ScheduledFlight} from '../scheduled-flight';
import { DatePipe } from '@angular/common';
import {Router} from '@angular/router';
import { from } from 'rxjs';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  user:User;
  noError:Boolean = true;
  scheduledFlightList:ScheduledFlight[];
  constructor(private _userService:UserService ,public datepipe: DatePipe,private _router:Router) { }

  ngOnInit() {
    
    this.user = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.user);
    if(this.user==null)
    {
      this._router.navigate(['login']);
    }
  }
  logout()
  {
    if (confirm("Do you want to logout of this account?"))
    {
      this._userService.setUserObject(null);
      sessionStorage.clear();
      this._router.navigate(['/login']);
    }
  }

  searchBooking(source,destination,date){
    let dateConvert = this.datepipe.transform(date,"dd-MM-yyyy");
    console.log(source+" "+destination+" "+dateConvert);
    this._userService.searchFlights(source,destination,dateConvert).subscribe((scheduledFlightList)=>{
        this.scheduledFlightList = scheduledFlightList;
       // console.log(this.scheduledFlightList);
    this._router.navigate(['/searchFlight']);
    this._userService.setScheduledFlightList(this.scheduledFlightList);
    },(error)=>{
      this.noError = false;
      console.log(error);
    })

  }
}
