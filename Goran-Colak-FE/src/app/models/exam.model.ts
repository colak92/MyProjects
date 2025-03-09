import { ExamPeriod } from './examperiod.model';
import { Professor } from './professor.model';
import { Subject } from './subject.model';

export class Exam {
  id: number;
  name: string;
  examdate: Date;
  examperiod?: ExamPeriod;
  examPeriodId?: number;
  examPeriodName?: string;
  subject?: Subject;
  subjectId?: number;
  subjectName?: string;
  professor?: Professor;
  professorId?: number;
  professorFirstName?: string;
  professorLastName?: string;
}