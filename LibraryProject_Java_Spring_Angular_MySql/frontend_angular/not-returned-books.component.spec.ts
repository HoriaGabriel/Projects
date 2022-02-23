import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotReturnedBooksComponent } from './not-returned-books.component';

describe('NotReturnedBooksComponent', () => {
  let component: NotReturnedBooksComponent;
  let fixture: ComponentFixture<NotReturnedBooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotReturnedBooksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotReturnedBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
