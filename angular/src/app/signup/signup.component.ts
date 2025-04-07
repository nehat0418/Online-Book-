import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  username = '';

  password = '';

  name = '';

  error = '';

  constructor(private userService: UserService, private router: Router) {}
// Method to handle the signup process
  async signup() {
    this.error = '';
    if(!this.validateEmail(this.username)) {
      this.error = 'Email is invalid';
      return;
    }
    try {
       // Call the signup method from the UserService using firstValueFrom to wait for the response
      const res = await firstValueFrom(this.userService.signup(this.username, this.name, this.password));
      sessionStorage.setItem("isLoggedIn", "true");
      sessionStorage.setItem("userId", res.user.userId);
      this.router.navigate(['/']); 
    } catch(err: any) {
    if(err.error.error) {
      this.error = err.error.error;
      return;
    }
  }
  }
  validateEmail = (email: string) => {
    return String(email)
      .toLowerCase()
      .match(
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
  };
}
