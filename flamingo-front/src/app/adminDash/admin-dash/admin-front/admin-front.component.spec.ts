import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFrontComponent } from './admin-front.component';

describe('AdminFrontComponent', () => {
  let component: AdminFrontComponent;
  let fixture: ComponentFixture<AdminFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
