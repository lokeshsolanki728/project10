import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-carlist',
  templateUrl: './carlist.component.html',
  styleUrls: ['./carlist.component.css']
})
export class CarlistComponent  extends BaseListCtl  implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      carList: [] // Initialize customer list
    },
    data: { id: null },
    inputerror: { price: '', ownerName: '',carName:'' },
    searchParams: {
      price: '',
      date: '', // Initialize date field
      modelId: null, // Initialize customerId to null
      carName: '',
      ownerName: '',
      purchaseDate: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.CAR, locator, route);
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
    const options:any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  // Validate input for quantity field
  validatePrice() {
    const price = this.form.searchParams.price;
    const isNumeric = /^\d+$/.test(price);
  
    if (price === null || !isNumeric || Number(price) <= 0 || Number(price) > 1000000) {
      this.form.inputerror.price = 'Invalid price Please enter a number between 1 and 1000000.';
    } else {
      this.form.inputerror.price = ''; // Clear error message if quantity is valid
    }
  }
  

  // Clear quantity input error message when field is cleared
  clearAmountError() {
    if (!this.form.searchParams.price) {
      this.form.inputerror.price = '';
    }
  }

  validateOwnerName(event: any) {
    const value = event.target.value.trim();
    const regex = /^[a-zA-Z]{1,20}$/; // Alphabetic characters only, length between 3 and 20

    if (value === '') {
      this.form.inputerror.ownerName = null; // Clear the error message if the field is empty
    } else if (!regex.test(value)) {
      this.form.inputerror.ownerName = 'Owner Name should contain only alphabetic characters and be between 3 to 20 characters long';
    } else {
      this.form.inputerror.ownerName = null;
      this.form.searchParams.ownerName = value;
    }
  }

  // Clear productName error on input field focus
  clearOwnerNameError() {
    this.form.inputerror.ownerName = null;
  }


  validateCarName(event: any) {
    const value = event.target.value.trim();
    const regex = /^[a-zA-Z]{1,20}$/; // Alphabetic characters only, length between 3 and 20

    if (value === '') {
      this.form.inputerror.carName = null; // Clear the error message if the field is empty
    } else if (!regex.test(value)) {
      this.form.inputerror.carName = 'Car Name should contain only alphabetic characters and be between 3 to 20 characters long';
    } else {
      this.form.inputerror.carName = null;
      this.form.searchParams.carName = value;
    }
  }

  // Clear productName error on input field focus
  clearCarNameError() {
    this.form.inputerror.ownerName = null;
  }

  // Submit method
  submit() {
    // Reset page number before searching
    this.form.pageNo = 0;

    // Check if there are any input errors
    if (this.form.inputerror.price || this.form.inputerror.ownerName|| this.form.inputerror.carName) {
      return; // Do not proceed with search if there are errors
    }

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
