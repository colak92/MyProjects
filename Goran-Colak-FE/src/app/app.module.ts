import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';

import { NgModule, NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
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
import { AddExamRegComponent } from './components/add-examreg/add-examreg.component';

import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardProfessorComponent } from './components/board-professor/board-professor.component';
import { BoardStudentComponent } from './components/board-student/board-student.component';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';

import { MatSortModule } from '@angular/material/sort';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';

import { ExamRegDialogComponent } from './components/examreg-dialog/examreg-dialog.component';
import { EnterExamGradeComponent } from './components/enter-examgrade/enter-examgrade.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    AddStudentComponent,
    EditStudentComponent,
    StudentDetailsComponent,
    CityListComponent,
    AddCityComponent,
    TitleListComponent,
    AddTitleComponent,
    ProfessorListComponent,
    AddProfessorComponent,
    EditProfessorComponent,
    ProfessorDetailsComponent,
    SubjectListComponent,
    AddSubjectComponent,
    EditSubjectComponent,
    SubjectDetailsComponent,
    ExamListComponent,
    AddExamComponent,
    EditExamComponent,
    ExamPeriodListComponent,
    AddExamPeriodComponent,
    EditExamPeriodComponent,
    ExamRegListComponent,
    AddExamRegComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardProfessorComponent,
    BoardStudentComponent,
    ConfirmDialogComponent,
    EditProfileComponent,
    ExamRegDialogComponent,
    EnterExamGradeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatSortModule,
    MatTooltipModule,
    MatDialogModule,
    MatTableModule,
    MatCheckboxModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    CommonModule
  ],
  exports: [ExamRegDialogComponent],
  providers: [DatePipe, authInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [ConfirmDialogComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
    NO_ERRORS_SCHEMA
  ]
})

export class AppModule {}
export class MaterialModule {}