import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTripListComponent } from './user-trip-list.component';

describe('UserTripListComponent', () => {
  let component: UserTripListComponent;
  let fixture: ComponentFixture<UserTripListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserTripListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserTripListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
