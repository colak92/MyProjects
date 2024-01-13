import { TestBed } from '@angular/core/testing';
import { ExamPeriodService } from './examperiod.service';

describe('ExamPeriodService', () => {
  let service: ExamPeriodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamPeriodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});