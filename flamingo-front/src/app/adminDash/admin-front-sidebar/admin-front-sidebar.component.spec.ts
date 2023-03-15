import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFrontSidebarComponent } from './admin-front-sidebar.component';

describe('AdminFrontSidebarComponent', () => {
  let component: AdminFrontSidebarComponent;
  let fixture: ComponentFixture<AdminFrontSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFrontSidebarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminFrontSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
