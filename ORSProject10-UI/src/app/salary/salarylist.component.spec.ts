import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalarylistComponent } from './salarylist.component';

describe('SalarylistComponent', () => {
  let component: SalarylistComponent;
  let fixture: ComponentFixture<SalarylistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalarylistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalarylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
