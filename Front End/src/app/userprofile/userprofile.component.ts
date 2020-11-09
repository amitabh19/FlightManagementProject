import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {


  public users: User[];
  mobileNo: string;
  password: string;
  balance: string;
  user = new User();
  usr = new User();
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {


    this.user = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.user);
    if(this.user==null)
    {
      this.router.navigate(['login']);
    }
  }
  deleteAccount() {
    if (confirm("Do you want to delete this account?")) {
      this.userService.deleteUserById(this.user.userId).subscribe(
        (u) => {
          this.users.splice(this.users.indexOf(this.user, 1));
        },

      )
      console.log("done");
      this.router.navigate(['signup']);
    }
    else {
      console.log("no");
    }

  }

  updateUser() {
    this.userService.setUserObject(this.user);
    this.router.navigate(['editDetails']);
  }


}
