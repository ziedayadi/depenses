import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousMonthsComponent } from './previous-months.component';

describe('PreviousMonthsComponent', () => {
  let component: PreviousMonthsComponent;
  let fixture: ComponentFixture<PreviousMonthsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreviousMonthsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviousMonthsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
