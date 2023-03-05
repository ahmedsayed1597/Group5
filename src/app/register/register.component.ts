import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private  _Router:Router, private _UserService:UserService, private _FormBuilder:FormBuilder) { }
  responseMessage:any;
  registerationForm:FormGroup;

  ngOnInit(): void {
    this.registerationForm = this._FormBuilder.group({
      name: new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(30)]),
      //phone: new FormControl('', [Validators.required, Validators.pattern(/^01[0-2,5]{1}[0-9]{8}$/)]),
      number: new FormControl(""),
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required]),
      password_repeat : new FormControl("", [Validators.required]),
    });
  }

  onSubmit(){
    if(this.registerationForm.valid == true){
      console.log(this.registerationForm.value)
      //let user = new User(this.registerationForm.value['name'],this.registerationForm.value['email'],this.registerationForm.value['password'],this.registerationForm.value['avatar'])
      this._UserService.signUp(this.registerationForm.value).subscribe({
        next: (response) => {
          console.log(this.registerationForm.value);
          this.responseMessage = response;
          console.log(this.responseMessage)
          if(response.message == 'User created'){
            this._Router.navigate(['Login'])
          }
        }
      ,
      error: (err) => {console.log(err);}
     });
     }
  }

  checkErrorInForm(input:string){
    if(this.registerationForm.controls[input].errors) return true;
    else return false;   
  }

  checkErrorInFormAndType(input:string, errorType:string){
    if(this.registerationForm.controls[input].hasError(errorType)) return true;
    else return false;
  }

  checkErrorIfFormTouch(input:string){
    if(this.registerationForm.controls[input].touched) return true;
    else return false;
  }
}

