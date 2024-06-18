import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddExamPeriodComponent } from './add-examperiod.component';

describe('AddExamPeriodComponent', () => {
  let component: AddExamPeriodComponent;
  let fixture: ComponentFixture<AddExamPeriodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddExamPeriodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExamPeriodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
