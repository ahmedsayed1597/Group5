import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private  _Router:Router, private _FormBuilder:FormBuilder, private _UserService:UserService) { }

  logInForm:FormGroup;
  responseMessage: any



  ngOnInit(): void {
    this.logInForm = this._FormBuilder.group({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required,]),
    });
  }

  onLogin(){
    if(this.logInForm.valid == true){
      console.log(this.logInForm.value)
      //let user = new User(this.registerationForm.value['name'],this.registerationForm.value['email'],this.registerationForm.value['password'],this.registerationForm.value['avatar'])
      this._UserService.signIn(this.logInForm.value).subscribe({
        next: (response) => {
          console.log(this.logInForm.value);
          this.responseMessage = response;
          console.log(this.responseMessage)
          // if(response.message == 'User created'){
          //   this._Router.navigate(['Home'])
          // }
        }
      ,
      error: (err) => {console.log(err);}
     });
     }
  }
}
