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
      this._UserService.signIn(this.logInForm.value).subscribe({
        next: (response) => {
          console.log(this.logInForm.value);
          this.responseMessage = response.message;
          console.log(this.responseMessage)
          if(response.message == 'Succesfull Login'){
            this._Router.navigate(['Home'])
          }
        }
      ,
      error: (err) => {console.log(err);}
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
