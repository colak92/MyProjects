import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { StudentListComponent } from './components/student-list/student-list.component';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { EditStudentComponent } from './components/edit-student/edit-student.component';
import { StudentDetailsComponent } from './components/student-details/student-details.component';

import { CityListComponent } from './components/city-list/city-list.component';
import { AddCityComponent } from './components/add-city/add-city.component';

import { TitleListComponent } from './components/title-list/title-list.component';
import { AddTitleComponent } from './components/add-title/add-title.component';

import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { AddProfessorComponent } from './components/add-professor/add-professor.component';
import { EditProfessorComponent } from './components/edit-professor/edit-professor.component';
import { ProfessorDetailsComponent } from './components/professor-details/professor-details.component';

import { SubjectListComponent } from './components/subject-list/subject-list.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { EditSubjectComponent } from './components/edit-subject/edit-subject.component';
import { SubjectDetailsComponent } from './components/subject-details/subject-details.component';

import { ExamListComponent } from './components/exam-list/exam-list.component';
import { AddExamComponent } from './components/add-exam/add-exam.component';
import { EditExamComponent } from './components/edit-exam/edit-exam.component';

import { ExamPeriodListComponent } from './components/examperiod-list/examperiod-list.component';
import { AddExamPeriodComponent } from './components/add-examperiod/add-examperiod.component';
import { EditExamPeriodComponent } from './components/edit-examperiod/edit-examperiod.component';

import { ExamRegListComponent } from './components/examreg-list/examreg-list.component';
import { SaveExamRegComponent } from './components/save-examreg/save-examreg.component';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardProfessorComponent } from './components/board-professor/board-professor.component';
import { BoardStudentComponent } from './components/board-student/board-student.component';

const routes: Routes = [
  {path: 'students', component: StudentListComponent},
  {path: 'add-student', component: AddStudentComponent},
  {path: 'edit-student/:id', component: EditStudentComponent},
  {path: 'student-details/:id', component: StudentDetailsComponent},

  {path: 'cities', component: CityListComponent},
  {path: 'add-city', component: AddCityComponent},

  {path: 'titles', component: TitleListComponent},
  {path: 'add-title', component: AddTitleComponent},

  {path: 'professors', component: ProfessorListComponent},
  {path: 'add-professor', component: AddProfessorComponent},
  {path: 'edit-professor/:id', component: EditProfessorComponent},
  {path: 'professor-details/:id', component: ProfessorDetailsComponent},

  {path: 'subjects', component: SubjectListComponent},
  {path: 'add-subject', component: AddSubjectComponent},
  {path: 'edit-subject/:id', component: EditSubjectComponent},
  {path: 'subject-details/:id', component: SubjectDetailsComponent},

  {path: 'exams', component: ExamListComponent},
  {path: 'add-exam', component: AddExamComponent},
  {path: 'edit-exam/:id', component: EditExamComponent},

  {path: 'examperiods', component: ExamPeriodListComponent},
  {path: 'add-examperiod', component: AddExamPeriodComponent},
  {path: 'edit-examperiod/:id', component: EditExamPeriodComponent},

  {path: 'examregs', component: ExamRegListComponent},
  {path: 'save-examreg/:id', component: SaveExamRegComponent},

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'professor', component: BoardProfessorComponent },
  { path: 'student', component: BoardStudentComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],                                                                                                                                                                                                                                                                                                          
  exports: [RouterModule]
})
export class AppRoutingModule { }
