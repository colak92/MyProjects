import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ExamRegListComponent } from './examreg-list.component';

describe('ExamRegListComponent', () => {
  let component: ExamRegListComponent;
  let fixture: ComponentFixture<ExamRegListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamRegListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamRegListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
