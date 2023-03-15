import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoaderService } from '../services/loader.service';
import { UserAuthService } from '../services/user-auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private  _Router:Router,
    private _FormBuilder:FormBuilder,
     private _UserService:UserService,
      public _LoaderService:LoaderService,
      private _userAuth:UserAuthService) { }

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
      let token = localStorage.getItem("jwtToken")
      this._UserService.signIn(this.logInForm.value).subscribe({
        next: (response) => {
          this.responseMessage = response.jwtToken;
          console.log(this.responseMessage)
          if(this.responseMessage !== ""){
            localStorage.setItem("jwtToken" , this.responseMessage)
            this._Router.navigate(['home']);
            this._userAuth. setRoles(this.responseMessage);
            console.log( this._userAuth.getRoles());
          }
        }
      ,
      error: (err) => {alert(err.message);
        console.log(err);
      }
     });
     }
  }

  checkErrorInForm(input:string){
    if(this.logInForm.controls[input].errors) return true;
    else return false;
  }

  checkErrorInFormAndType(input:string, errorType:string){
    if(this.logInForm.controls[input].hasError(errorType)) return true;
    else return false;
  }

  checkErrorIfFormTouch(input:string){
    if(this.logInForm.controls[input].touched) return true;
    else return false;
  }

}

