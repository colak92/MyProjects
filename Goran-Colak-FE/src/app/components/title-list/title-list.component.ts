import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from 'src/app/models/title.model';
import { TitleService } from 'src/app/services/title.service';

@Component({
  selector: 'app-title-list',
  templateUrl: './title-list.component.html',
  styleUrls: ['./title-list.component.css']
})

export class TitleListComponent implements OnInit {

  titles: Title[] = [];

  constructor(private titleService: TitleService, private router: Router) { }

  ngOnInit(): void {
    this.getTitles();
  }

  addTitle(){
    this.router.navigate(['add-title']);
  }

  getTitles(){
    this.titleService.getTitleList().subscribe(data => {
      this.titles = data;
    });
  }

  deleteTitle(id: number){
    this.titleService.deleteTitle(id).subscribe( data => {
      console.log(data);
      this.getTitles();
    })
  }
}
