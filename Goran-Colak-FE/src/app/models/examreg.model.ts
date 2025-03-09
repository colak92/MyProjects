import { Exam } from './exam.model';
import { Student } from './student.model';

export class ExamReg {
    id?: number;
    grade?: number;
    passed?: Boolean;
    comment?: string;
    examregdate: Date;
    student: Student;
    studentId?: number;
    studentFirstName?: string;
    studentLastName?: string;
    exam?: Exam;
    examId?: number;
    examName?: string;
  }