import { Component, OnInit } from '@angular/core';
import { NgModule }      from '@angular/core';
    import { BrowserModule } from '@angular/platform-browser';
    // import { FormBuilder} from '@angular/forms';  
import { BackendApiService } from '../services/backend-api.service';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css']
})
export class CheckInComponent implements OnInit {

  usersName: string = '';
  response: any;

  constructor(private services: BackendApiService) { }

  ngOnInit(): void {
    this.usersName = '';
  }

  checkIn(){
    this.response = "";
   this.response =  this.services.getCheckIn(this.usersName);
  }
  checkOut(){
    this.response = "";
    this.response =  this.services.getCheckOut(this.usersName);
  }
  view(){
    this.response = "";
    this.response =  this.services.getView(this.usersName);
  }

}
