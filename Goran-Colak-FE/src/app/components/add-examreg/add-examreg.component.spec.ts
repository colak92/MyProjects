import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddExamRegComponent } from './add-examreg.component';

describe('AddExamRegComponent', () => {
  let component: AddExamRegComponent;
  let fixture: ComponentFixture<AddExamRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddExamRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExamRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});