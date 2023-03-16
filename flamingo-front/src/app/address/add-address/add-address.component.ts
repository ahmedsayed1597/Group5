import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AddressService } from 'src/app/services/address.service';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.scss']
})
export class AddAddressComponent implements OnInit {

  constructor(private addressService:AddressService) { }

  ngOnInit(): void {
  }

  onSubmit(AddressForm : any){
    console.log(AddressForm.value);
    this.addressService.addAddress()
  }

}
