import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExamRegDialogComponent } from './examreg-dialog.component';

describe('ExamRegDialogComponent', () => {
  let component: ExamRegDialogComponent;
  let fixture: ComponentFixture<ExamRegDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamRegDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamRegDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
