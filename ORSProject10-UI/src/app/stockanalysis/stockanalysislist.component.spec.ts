import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockanalysislistComponent } from './stockanalysislist.component';

describe('StockanalysislistComponent', () => {
  let component: StockanalysislistComponent;
  let fixture: ComponentFixture<StockanalysislistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockanalysislistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockanalysislistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
