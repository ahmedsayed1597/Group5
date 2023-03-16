import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderOneComponent } from './order-one.component';

describe('OrderOneComponent', () => {
  let component: OrderOneComponent;
  let fixture: ComponentFixture<OrderOneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderOneComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
