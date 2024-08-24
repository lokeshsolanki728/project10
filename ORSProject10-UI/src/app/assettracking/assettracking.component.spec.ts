import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssettrackingComponent } from './assettracking.component';

describe('AssettrackingComponent', () => {
  let component: AssettrackingComponent;
  let fixture: ComponentFixture<AssettrackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssettrackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssettrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
