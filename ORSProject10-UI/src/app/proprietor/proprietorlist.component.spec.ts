import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProprietorlistComponent } from './proprietorlist.component';

describe('ProprietorlistComponent', () => {
  let component: ProprietorlistComponent;
  let fixture: ComponentFixture<ProprietorlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProprietorlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProprietorlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
