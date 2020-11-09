import { Component, Input } from '@angular/core';
import { User } from './user';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls:['app.component.css']
})

export class AppComponent{
    message="Sent From Parent";
    result="";

     @Input() userSet :Boolean = true;
 
    
 }