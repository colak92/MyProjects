import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { EnterExamGradeComponent } from './enter-examgrade.component';

describe('EnterExamGradeComponent', () => {
  let component: EnterExamGradeComponent;
  let fixture: ComponentFixture<EnterExamGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterExamGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterExamGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
