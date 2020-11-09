import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  users: User[];
  usr = new User();
  email: string;
  password: string;
  error: string;
  user: User;
  user1 = new User();
  tusername = new User();
  test: User;

  constructor(private _userService: UserService, public datepipe: DatePipe, private _router: Router) { }

  ngOnInit() {
    this.tusername = this._userService.getUserObject();
    //sessionStorage.setItem('current user', JSON.stringify(this.tusername));
    this.test = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.test);
  }


  processForm() {
    
    this._userService.login(this.email, this.password).
      then(
        (data: User) => {
          this.user = data;
          console.log(this.user);
        }, (error) => {
           /* console.log(
            `Backend returned code ${error.status}, ` +
            `body was: ${JSON.stringify(error.error)}`);
            */
           console.log(error);
        }).then
      ((redf) => {
        if (this.user == null) {
          this.error = "Please enter the correct credentials";
        }
        else {
          if (this.user.userType == "admin") {
            alert("now being navigated to admin page");
            this._router.navigate(['adminHome']);
          }
          else {
            console.log("logged in");
            this._userService.setUserObject(this.user);
            sessionStorage.setItem('current user', JSON.stringify(this.user));
            this._router.navigate(['showHome']);
          }
        }
      });
  }

}
