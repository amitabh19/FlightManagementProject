import { Injectable } from '@angular/core';
import {Http,Response,Headers,RequestOptions} from '@angular/http';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators'; 
import { Observable, throwError, from } from 'rxjs';
import {User} from './user';
import {ScheduledFlight} from'./scheduled-flight';
import { Booking } from './booking';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl:String = 'http://localhost:8080/api';
  private headers = new Headers({'Content-Type':'application/json'});
  private options1 = new RequestOptions({headers:this.headers})
  private user:User;
  private scheduledFlightList: ScheduledFlight[];
  private scheduledFlight:ScheduledFlight;
  constructor(private http:HttpClient, private _http:Http) { }
  
  private options = {
    headers: new HttpHeaders().append('Content-Type', 'application/json'),
  }

  getUserById(id: Number)
  {
    return this.http.get(this.baseUrl+"/viewUser/"+ id ,this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }
  
  getUsers(): Observable<User[]> {

    return this.http.get<User[]>(this.baseUrl + '/users').pipe(catchError(this.errorHandler));
  }

  addUser(user: User) {
    return this.http.post(this.baseUrl + "/addUser", JSON.stringify(user), this.options).pipe(catchError(this.errorHandler));
  }

  updateUser(user: User) {
    return this.http.put(this.baseUrl + '/user', JSON.stringify(user), this.options).pipe(catchError(this.errorHandler));;
  }

  deleteUserById(id: Number) {
    return this.http.delete(this.baseUrl + '/deleteUser/' + id).pipe(catchError(this.errorHandler));;
  }

  
  searchFlights(source:String,destination:String,date:String){
   let sourceObject={
      "arrival":source,
      "departure":destination,
      "date":date
    }
 
    return this._http.post(this.baseUrl+"/search",sourceObject,this.options1).pipe(map((response:Response)=>response.json()))
    .pipe(catchError(this.errorHandler));
  }
 
 
  setScheduledFlightList(flights:ScheduledFlight[]){
    this.scheduledFlightList=flights;
    console.log("In setter:" + this.scheduledFlightList);
    console.log("In setter:" + flights);
  }
  getScheduledFlight(){
    console.log("In getter: "+this.scheduledFlightList);
    return this.scheduledFlightList;
  }
 
  getBookingsById(id: Number){
    return this._http.post(this.baseUrl+"/getBookings/"+id,this.options1).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }
 
  makeBooking(booking:Booking){
    return this._http.post(this.baseUrl+"/createBooking",booking,this.options1).pipe(map((response:Response)=>response.json()))
    .pipe(catchError(this.errorHandler));
  }
 
  setUserObject(user:User){
    this.user = user;
  }
  getUserObject(){
    return this.user;
  }
 
  setScheduledFlightObj(scheduledFlight:ScheduledFlight){
    this.scheduledFlight = scheduledFlight;
  }
 
  getScheduledFlightObj(){
      return this.scheduledFlight;
  }

  login(email: String, password: String):Promise<any>
  {
    let authLogin={
      "email":email,
      "password":password,
    }
    return this.http.post(this.baseUrl + "/login", authLogin, this.options).pipe(catchError(this.errorHandler1)).toPromise();
  }
 errorHandler1(error: HttpErrorResponse) {
      return throwError(error || "SERVER ERROR");
    }

  errorHandler(error: HttpErrorResponse){
    return  throwError(error || "SERVER ERROR");  
    }
    
}
