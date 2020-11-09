import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-details-editor',
  templateUrl: './user-details-editor.component.html',
  styleUrls: ['./user-details-editor.component.css']
})
export class UserDetailsEditorComponent implements OnInit {

  flag:boolean=true;
  valid:boolean=true;

  user:User;
  users:User[];
  oldPass:String;
  errorEmail:String;
  errorMobile:String;
  errorMobile1:String;
  errorUname:String;
  errorPassword:String;
  errorEmail1:String;
  error:String;
 
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(){
    this.user = JSON.parse(sessionStorage.getItem('current user'));
    console.log(this.user);
    if(this.user==null)
    {
      this.router.navigate(['login']);
    }
    this.oldPass = this.user.userPassword;
    console.log(this.user);

    this.userService.getUsers().subscribe((data: any[])=>{
      this.users = data;
    }
    );
  }
  update(){
    this.flag=true;
    this.valid=true;
    this.error=null;
    this.errorEmail=null;
    this.errorMobile=null;
    this.errorUname=null;
    this.errorPassword=null;
    this.errorEmail1=null;
    this.errorMobile=null;
    let regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
  
    if(this.user.userName.length<3 )
    {
          this.flag = false;
          this.error = "name cannot be less than 3 characters";
    }

    if(!this.user.email.match(regexp))
    {
        this.flag = false;
        this.errorEmail1 = "email pattern incorrect";  
    }

    if(this.user.userPassword.length<8)
    {
      this.flag = false;
      this.errorPassword = "password cannot be less than 8 characters";
    }

    if(this.user.userPhone.length!=10)
    {
      this.flag = false;
      this.errorMobile1 = "mobile number length should be 10 digits";
    }

    for(let c of this.users){

      if(c.userId!=this.user.userId )
      {
        if(c.email == this.user.email)
        {
          this.flag = false;
          this.errorEmail = "email is already registered with another user";
        }
        if(c.userPhone == this.user.userPhone)
        {
          this.flag = false;
          this.errorMobile = "mobile no is already registered with another user";
        }

        if(c.userName == this.user.userName)
        {
          this.flag = false;
          this.errorUname = "username is already registered with another user";
        }
      }
      
      
    }

    if(this.flag == false)
      {
         this.valid = false;
      }
    if(this.flag == true)
    {
      this.userService.updateUser(this.user).subscribe( (data:User) => {this.user=data});
      this.userService.setUserObject(this.user);
      console.log(this.user);
      alert("user details updated");
      this.router.navigate(['showHome']);
    }
  }

}
