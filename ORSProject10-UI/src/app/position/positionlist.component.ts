import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-positionlist',
  templateUrl: './positionlist.component.html',
  styleUrls: ['./positionlist.component.css']
})
export class PositionlistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      assetIdList: [] // Initialize vehicle list
    },
    data: { id: null },
   // inputerror: { quantity: '', productName: '' },
    searchParams: {
      openingDate: '',
      designation: '',
      date: '', // Initialize date field
      conditionId: null, // Initialize vehicleId to null
      requiredExperience: '',
      
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };
 
  isValidMobileInput: boolean = true;
  isValidNameInput: boolean = true;
  nameErrorMessage: string = '';
  mobileErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.POSITION, locator, route);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  // Format date function
  formatDate(event: any) {
    const selectedDate = new Date(event);
    const formattedDate = selectedDate.toISOString().split('T')[0];
    this.form.searchParams.date = formattedDate;
  }

  // Convert date to local format for display
  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    // Format date to 'MM/DD/YYYY'
    const options:any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  // Validate input for name and mobile fields
 
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
  // Submit method
  submit() {
    // Reset page number before searching
    this.form.pageNo = 0;
    // Call the search method after formatting the date
    this.search();
  }


  // Search method
  search() {
    // Clear previous search message
    this.form.searchMessage = null;
    // Perform the search operation with the search parameters
    super.search();
  }
}

