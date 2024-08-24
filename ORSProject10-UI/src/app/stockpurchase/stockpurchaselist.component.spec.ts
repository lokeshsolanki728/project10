import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockpurchaselistComponent } from './stockpurchaselist.component';

describe('StockpurchaselistComponent', () => {
  let component: StockpurchaselistComponent;
  let fixture: ComponentFixture<StockpurchaselistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockpurchaselistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockpurchaselistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
