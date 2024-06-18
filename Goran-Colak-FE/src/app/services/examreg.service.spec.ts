import { ExamRegService } from './examreg.service';
import { TestBed } from '@angular/core/testing';

describe('ExamRegService', () => {
  let service: ExamRegService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamRegService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});