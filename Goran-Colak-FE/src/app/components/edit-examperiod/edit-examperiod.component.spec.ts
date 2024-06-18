import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { EditExamPeriodComponent } from './edit-examperiod.component';

describe('EditExamPeriodComponent', () => {
  let component: EditExamPeriodComponent;
  let fixture: ComponentFixture<EditExamPeriodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditExamPeriodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditExamPeriodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
