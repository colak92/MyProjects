import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { SaveExamRegComponent } from './save-examreg.component';

describe('SaveExamRegComponent', () => {
  let component: SaveExamRegComponent;
  let fixture: ComponentFixture<SaveExamRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveExamRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveExamRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});