import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HttpModule } from '@angular/http';
import {ReactiveFormsModule,FormBuilder} from '@angular/forms';;
import { HomepageComponent } from './homepage/homepage.component'
import { DatePipe } from '@angular/common';

import {Routes,RouterModule} from '@angular/router';
import {UserService} from './user.service';;
import { SearchListComponent } from './search-list/search-list.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { UserDetailsEditorComponent } from './user-details-editor/user-details-editor.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';



const appRoutes:Routes = [
    {path:'showHome',component:HomepageComponent},
    {path:'searchFlight',component:SearchListComponent},
    {path:'viewBookings',component:ViewBookingComponent},
    {path:'createBooking',component:CreateBookingComponent},
    {path:'',component:LoginComponent},
    {path:'signup',component:SignupComponent},
    {path:'login',component:LoginComponent},
    {path:'editDetails',component:UserDetailsEditorComponent},
    {path:'userProfile',component:UserprofileComponent},
    {path:'adminHome',component:AdminHomeComponent},
    


]
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        HttpModule,
        RouterModule.forRoot(appRoutes),
        
    ],
    declarations: [
        AppComponent,
        HomepageComponent,
        SearchListComponent ,
        ViewBookingComponent ,
        CreateBookingComponent ,
        LoginComponent ,
        SignupComponent ,
        UserDetailsEditorComponent ,
        UserprofileComponent ,
        AdminHomeComponent 
       
		],
    providers: [UserService,DatePipe],
    bootstrap: [AppComponent]
})

export class AppModule { }