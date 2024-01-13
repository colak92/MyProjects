import { ExamPeriod } from './examperiod.model';
import { Professor } from './professor.model';
import { Subject } from './subject.model';
import { Student } from './student.model';

export class Exam {
  id: number;
  name: string;
  examdate: Date;
  grade: number;
  passed: Boolean;
  registered: Boolean;
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
  student?: Student;
  studentId?: number;
  studentFirstName?: string;
  studentLastName?: string;
}