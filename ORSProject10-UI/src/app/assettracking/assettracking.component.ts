import { Component, OnInit } from '@angular/core';
import { BaseCtl } from '../base.component';
import { FormGroupName } from '@angular/forms';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-assettracking',
  templateUrl: './assettracking.component.html',
  styleUrls: ['./assettracking.component.css']
})
export class AssettrackingComponent extends BaseCtl implements OnInit {

  getKey = false;
  selected = null;
  userForm: FormGroupName = null;
  uploadForm: FormGroupName;
  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ASSETTRACKING, locator, route);
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
        console.log("----------Rahul----------.");
  
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
    flag = flag && validator.isNotNullObject(form.vehicleIdId);
    console.log(form.vehicleIdId);
    flag = flag && validator.isNotNullObject(form.name);
    console.log(form.name);
    flag = flag && validator.isNotNullObject(form.lat);
    console.log(form.lat);
    flag = flag && validator.isNotNullObject(form.longitude);
    console.log(form.longitude);
    flag = flag && validator.isNotNullObject(form.date);
    console.log(form.date);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    //form.name = data.name;
    form.latitude= data.latitude;
    form.longitude = data.longitude;
    form.trackingDate = data.trackingDate;
    form.assetId = data.assetId;


  }

  validatePhone(event: KeyboardEvent) {
    const input = event.key;
    // Check if the input is a number or backspace
    if ((isNaN(Number(input)) && input !== 'Backspace') || (input === ' ')) {
      event.preventDefault();
    }
    // Limit the input to 10 characters
    if (this.form.data.phone && this.form.data.phone.length >= 10 && input !== 'Backspace') {
      event.preventDefault();
    }
  }

  validateAlphabetInput(event) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a letter
    const letterRegex = /^[a-zA-Z\s]+$/;

    // Test if the input matches the allowed characters
    if (!letterRegex.test(charStr)) {
      event.preventDefault();
    }

    // Optionally, check the entire value against the name format regex
    const inputElement = event.target;
    const inputValue = inputElement.value + charStr; // Add the current character to the input value

    // Regex for valid name format "FirstName LastName"
    const nameRegex = /^[A-Z][a-z]+ [A-Z][a-z]+$/;

    // Check if the current value matches the valid name format
    if (!nameRegex.test(inputValue)) {
      // Handle invalid input (e.g., disable submit button, show error message)
      // Example:
      inputElement.classList.add('invalid'); // Apply CSS class for invalid input
    } else {
      inputElement.classList.remove('invalid'); // Remove invalid CSS class if format is valid
    }
  }

  validateCoordinateInput(event: any, field: string) {
    const value = event.target.value.trim(); // Trim whitespace

    if (field === 'latitude') {
      this.handleLatitudeInput(event, value);
    } else if (field === 'longitude') {
      this.handleLongitudeInput(event, value);
    }
  }

  handleLatitudeInput(event: any, value: string) {
    const isValid = this.isValidLatitude(value);

    if (!isValid) {
      event.preventDefault();
      event.target.value = ''; // Clear the input if it's invalid
    }
  }

  handleLongitudeInput(event: any, value: string) {
    const isValid = this.isValidLongitude(value);

    if (!isValid) {
      event.preventDefault();
      event.target.value = ''; // Clear the input if it's invalid
    }
  }

  isValidLatitude(value: string): boolean {
    const numValue = parseFloat(value);
    return /^-?\d{1,2}(\.\d{1,6})?$/.test(value) && numValue >= -90 && numValue <= 90;
  }

  isValidLongitude(value: string): boolean {
    const numValue = parseFloat(value);
    return /^-?\d{1,3}(\.\d{1,6})?$/.test(value) && numValue >= -180 && numValue <= 180;
  }

  handleInput(event: any, field: string) {
    let value = event.target.value;

    if (field === 'latitude' || field === 'longitude') {
      // Allow only digits, minus sign, and a single dot
      value = value.replace(/[^0-9.-]/g, '');

      // Prevent multiple dots
      const dotCount = value.split('.').length - 1;
      if (dotCount > 1) {
        value = value.substring(0, value.lastIndexOf('.'));
      }

      // Prevent multiple minus signs
      const minusCount = value.split('-').length - 1;
      if (minusCount > 1 || (minusCount === 1 && value.indexOf('-') > 0)) {
        value = value.replace(/-/g, '');
      }

      event.target.value = value; // Update the input value
    }
  }


  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }
  test() {

  }

}
