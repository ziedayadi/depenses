import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeCardComponent } from './charge-card.component';

describe('ChargeCardComponent', () => {
  let component: ChargeCardComponent;
  let fixture: ComponentFixture<ChargeCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChargeCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChargeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
