import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject.model';
import { SubjectService } from 'src/app/services/subject.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css']
})
export class SubjectDetailsComponent implements OnInit {

  id: number;
  subject: Subject = new Subject;
  constructor(private route: ActivatedRoute, private subjectService: SubjectService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.subject = new Subject();
    this.subjectService.getSubjectById(this.id).subscribe( data => {
      this.subject = data;
    });
  }

}
