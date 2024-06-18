import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/services/professor.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css']
})
export class ProfessorDetailsComponent implements OnInit {

  id: number;
  professor: Professor = new Professor;
  constructor(private route: ActivatedRoute, private professorService: ProfessorService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.professor = new Professor();
    this.professorService.getProfessorById(this.id).subscribe( data => {
      this.professor = data;
    });
  }

}
