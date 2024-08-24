import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-salarylist',
  templateUrl: './salarylist.component.html',
  styleUrls: ['./salarylist.component.css']
})
export class SalarylistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      salaryList: [] // Initialize customer list
    },
    data: { id: null },
    inputerror: { amount: '', employeeName: '' },
    searchParams: {
      amount: '',
      date: '', // Initialize date field
      statusId: null, // Initialize customerId to null
      employeeName: '',
      appliedDate: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.SALARY, locator, route);
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
  validateAmount() {
    const amount = this.form.searchParams.amount;
    const isNumeric = /^\d+$/.test(amount);
  
    if (amount === null || !isNumeric || Number(amount) <= 0 || Number(amount) > 1000000) {
      this.form.inputerror.amount = 'Invalid amount Please enter a number between 1 and 1000000.';
    } else {
      this.form.inputerror.amount = ''; // Clear error message if quantity is valid
    }
  }
  

  // Clear quantity input error message when field is cleared
  clearAmountError() {
    if (!this.form.searchParams.amount) {
      this.form.inputerror.amount = '';
    }
  }

  validateEmployeeName(event: any) {
    const value = event.target.value.trim();
    const regex = /^[a-zA-Z]{1,20}$/; // Alphabetic characters only, length between 3 and 20

    if (value === '') {
      this.form.inputerror.employeeName = null; // Clear the error message if the field is empty
    } else if (!regex.test(value)) {
      this.form.inputerror.employeeName = 'Employee Name should contain only alphabetic characters and be between 3 to 20 characters long';
    } else {
      this.form.inputerror.employeeName = null;
      this.form.searchParams.employeeName = value;
    }
  }

  // Clear productName error on input field focus
  clearEmployeeNameError() {
    this.form.inputerror.employeeName = null;
  }

  // Submit method
  submit() {
    // Reset page number before searching
    this.form.pageNo = 0;

    // Check if there are any input errors
    if (this.form.inputerror.amount || this.form.inputerror.employeeName) {
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
