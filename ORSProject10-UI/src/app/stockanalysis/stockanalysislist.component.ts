import { Component, OnInit } from '@angular/core';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BaseListCtl } from '../base-list.component';

@Component({
  selector: 'app-stockanalysislist',
  templateUrl: './stockanalysislist.component.html',
  styleUrls: ['./stockanalysislist.component.css']
})
export class StockanalysislistComponent  extends BaseListCtl implements OnInit {
  isInvalid :boolean=false;
  public form = {
    error: false,
    message: null,
    preload: {
      stockAnalysisList: [] 
    },
    data: { id: null },
    inputerror: {stockSymbol:"",},
    searchParams: {
     stockSymbol: '',
      date: '', 
      analysisTypeId: null, 
     // version: '',
      startDate :'',
      endDate :''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };


  isValidVersionInput: boolean = true;
  isValidNameInput: boolean = true;
  nameErrorMessage: string = '';
  versionErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.STOCKANALYSIS, locator, route);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  // Format date function
  formatDate(event: any) {
    const selectedDate = new Date(event);
    const formattedDate = selectedDate.toISOString().split('T')[0]; // Ensure it's in ISO format
    this.form.searchParams.date = formattedDate;
  }


  // Convert date to local format for display
  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    // Format date to 'MM/DD/YYYY'
    const options:any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }


  validateAlphanumericInput(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    const inputValue = inputElement.value;

    // Regular expression to allow only alphanumeric characters
    const alphanumericRegex = /^[a-zA-Z0-9]*$/;

    // Validate the entire input value
    if (!alphanumericRegex.test(inputValue)) {
      this.isInvalid = true;
      inputElement.value = inputValue.replace(/[^a-zA-Z0-9]/g, ''); // Remove non-alphanumeric characters
    } else {
      this.isInvalid = inputValue.length < 6; // Check if the input is valid based on length
    }
  }
  // Validate input for name and mobile fields
  validateInput(event: any, field: string) {
    const value = event.target.value;
    if (field === 'version') {
    this.isValidVersionInput = value.replace(/[^0-9.]/g, ''); // Allow only numbers and dots
    if (!this.isValidVersionInput) {
      this.versionErrorMessage = '';
    }
    } else if (field === 'name') {
      this.isValidNameInput = /^[A-Za-z\s]*$/.test(value); // Check if the input contains only letters and spaces
      if (!this.isValidNameInput) {
        this.nameErrorMessage = 'Please type alphabets only';
      } else {
        this.nameErrorMessage = '';
      }
    }
  }

  validateNumericInput(event: KeyboardEvent) {
    const inputElement = event.target as HTMLInputElement;
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);
  
    // Regular expression to test if the character is a number, dot, or comma
    const allowedRegex = /^[0-9.,]$/;
  
    // Test if the input matches the allowed characters and if the current input length is less than 10
    if (!allowedRegex.test(charStr) || inputElement.value.length >= 10) {
      event.preventDefault();
    }
  }
  validateAlphabetInput(event: KeyboardEvent) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a letter
    const letterRegex = /^[a-zA-Z\s]+$/;

    // Test if the input matches the allowed characters
    if (!letterRegex.test(charStr)) {
      event.preventDefault();
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

