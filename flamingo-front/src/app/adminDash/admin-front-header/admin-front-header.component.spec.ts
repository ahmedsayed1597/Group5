import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFrontHeaderComponent } from './admin-front-header.component';

describe('AdminFrontHeaderComponent', () => {
  let component: AdminFrontHeaderComponent;
  let fixture: ComponentFixture<AdminFrontHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFrontHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminFrontHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
