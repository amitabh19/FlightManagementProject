import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {


  users: User[];
  user = new User();
  //name:string;
  id: number;
  accountBool: Boolean = true;
  phoneBool: Boolean = true;
  userBool: Boolean = true;
  emailBool: Boolean = true;
  mobile: string;
  user1 = new User();
  signupForm: FormGroup;
  constructor(private userService: UserService, private router: Router, private fb: FormBuilder) { }

  ngOnInit() {

    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      mobileNo: ['', [Validators.required, Validators.pattern("[0-9]*"), Validators.minLength(10), Validators.maxLength(10)]],
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]]

    });

    this.userService.getUsers().subscribe((data: any[]) => {
      //console.log(data);
      this.users = data;

    }
    );
  }


  onSubmit() {
    let valid: boolean
    let id: Number
    for (let c of this.users) {
      id = c.userId;
      if (c.userName == this.signupForm.value.username) {
        console.log("Username Already Taken");
        valid = false;
        this.userBool = false;
        break;
      }
      else if (c.email == this.signupForm.value.email) {
        console.log("Email Number Already Taken");
        valid = false;
        this.emailBool = false;
        break;
      }


      else if (c.userPhone == this.signupForm.value.mobileNo) {
        console.log("Phone Number Already Taken");
        valid = false;
        this.phoneBool = false;
        break;
      }
      else {
        valid = true;
      }
    }
    if (valid == true) {

      console.log(this.signupForm.value.email + " " + name + " " + " " + this.signupForm.value.mobileNo + " " + this.signupForm.value.amount + " " + this.signupForm.value.username + " " + this.signupForm.value.password);
      this.user.userId = undefined;
      this.user.userPhone = this.signupForm.value.mobileNo;
      this.user.userName = this.signupForm.value.username;
      this.user.userPassword = this.signupForm.value.password;
      this.user.email = this.signupForm.value.email;
      this.user.userType = "user";
      this.userService.addUser(this.user).subscribe(x => console.log(x));
      console.log("inserted");
      this.userService.setUserObject(this.user);
      sessionStorage.setItem('current user', JSON.stringify(this.user));
      this.router.navigate(['login']);
    }
    else {
      console.log("Not Inserted")
    }



  }

}
