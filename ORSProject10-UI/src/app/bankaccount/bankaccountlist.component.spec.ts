import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankaccountlistComponent } from './bankaccountlist.component';

describe('BankaccountlistComponent', () => {
  let component: BankaccountlistComponent;
  let fixture: ComponentFixture<BankaccountlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankaccountlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankaccountlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
