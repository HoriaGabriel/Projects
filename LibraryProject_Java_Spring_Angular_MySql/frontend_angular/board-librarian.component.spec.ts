import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardLibrarianComponent } from './board-librarian.component';

describe('BoardLibrarianComponent', () => {
  let component: BoardLibrarianComponent;
  let fixture: ComponentFixture<BoardLibrarianComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardLibrarianComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardLibrarianComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
