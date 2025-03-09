import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  id: number;
  user: User = new User();

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(){
    this.id = this.route.snapshot.params['id'];
    this.user = new User();
    this.userService.getProfileById(this.id).subscribe( data => {
    this.user = data;
    });
  }

  updateProfile(id: number) {
    this.router.navigate(['edit-profile', id]);
  }

}
