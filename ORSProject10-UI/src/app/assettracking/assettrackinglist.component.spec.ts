import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssettrackinglistComponent } from './assettrackinglist.component';

describe('AssettrackinglistComponent', () => {
  let component: AssettrackinglistComponent;
  let fixture: ComponentFixture<AssettrackinglistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssettrackinglistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssettrackinglistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
