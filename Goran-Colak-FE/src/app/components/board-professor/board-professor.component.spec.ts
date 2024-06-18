import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BoardProfessorComponent } from './board-professor.component';

describe('BoardProfessorComponent', () => {
  let component: BoardProfessorComponent;
  let fixture: ComponentFixture<BoardProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardProfessorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
