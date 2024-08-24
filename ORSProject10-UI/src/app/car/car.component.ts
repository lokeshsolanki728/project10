import { Component, OnInit } from '@angular/core';
import { BaseCtl } from '../base.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent extends BaseCtl implements OnInit {

  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;

  constructor(public locator: ServiceLocatorService, private formBuilder: FormBuilder, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.CAR, locator, route);
  }

  submit() {
    var _self = this;
    console.log('in submit');
    console.log(this.form);
    console.log(this.form.data);

    this.serviceLocator.httpService.post(this.api.save, this.form.data, function (res) {
      _self.form.message = '';
      _self.form.inputerror = {}; // Clear input errors here

      if (res.success) {
        _self.form.error = false; // Set error to false on success
        _self.form.message = "Data is saved";
        _self.form.data.id = res.result.data;
        console.log(_self.form.data.id);
       

        // Clear form data if needed
        // _self.form.data = {};

      } else {
        _self.form.error = true;
        if (res.result.inputerror) {
          _self.form.inputerror = res.result.inputerror;
        }
        _self.form.message = res.result.message;
      }
      console.log('FORM', _self.form);
    });
  }

  submit1() {
    var _self = this;
    console.log(this.form + "submit running start");
    console.log(this.form.data + "form data going to be submit");
    this.serviceLocator.httpService.post(this.api.search, this.form.data, function (res) {
      _self.form.message = '';
      _self.form.inputerror = {};
      _self.form.data.id = res.result.data;

      if (res.success) {
        _self.form.message = "Data is saved";
        _self.form.data.id = res.result.data;

        console.log(_self.form.data.id);
        console.log("--------------------.");

      } else {
        _self.form.error = true;
        _self.form.inputerror = res.result.inputerror;
        _self.form.message = res.result.message;
      }
      _self.form.data.id = res.result.data;
      console.log('FORM', _self.form);
    });
  }

  onUpload(userform: FormData) {
    this.submit();
    console.log(this.form.data.id + '---- after submit');
  }

  validateForm(form) {
    let flag = true;
    let validator = this.serviceLocator.dataValidator;
    flag = flag && validator.isNotNullObject(form.modelId);
    flag = flag && validator.isNotNullObject(form.price);
     flag = flag && validator.isNotNullObject(form.ownerName);
   flag = flag && validator.isNotNullObject(form.carName);
    flag = flag && validator.isNotNullObject(form.purchaseDate);
   

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.ownerName = data.ownerName;
    form.carName = data.carName;
    form.price = data.price;
    form.purchaseDate = data.purchaseDate;
    form.modelId = data.modelId;
  }

  validateOwnerName(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    // Allow alphabetic characters and spaces
    const pattern = /^[a-zA-Z\s]*$/;
    const ownerName = input.value;
    const key = event.key;

    // Check if the key matches the pattern (alphabetic or space)
    if (!pattern.test(key)) {
      event.preventDefault();
    }

    // Ensure the total length is between 3 and 31
    // We add 1 because the key being pressed hasn't been added to the input value yet
    if (ownerName.length + 1 > 21) {
      event.preventDefault();
    }
    
    // Ensure the length is not less than 3
    // This part is more relevant when submitting the form rather than on key press
    if (ownerName.length + 1 < 3 && key === 'Enter') {
      event.preventDefault();
    }
}


  validatePrice(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    const pattern = /^[0-9]*$/;
    const key = event.key;

    // Allow numbers only
    if (!pattern.test(key)) {
      event.preventDefault();
    }

    // Ensure the value is between 1 and 1000
    const price = input.value + key;
    if (parseInt(price) > 10000000) {
      event.preventDefault();
    }
  }

  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }


}
