import { Component } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'goran-colak-fe';

  private roles: string[] = [];
  isLoggedIn = false;
  isAdmin = false;
  isProfessor = false;
  isStudent = false;

  showAdminBoard = false;
  showProfessorBoard = false;
  showStudentBoard = false;
  username?: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      console.info('Roles: ' + user.roles);

      if (this.roles.includes('ADMIN')){
        this.isAdmin = true;
      }

      if (this.roles.includes('PROFESSOR')){
        this.isProfessor = true;
      }

      if (this.roles.includes('STUDENT')){
        this.isStudent = true;
      }

      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
