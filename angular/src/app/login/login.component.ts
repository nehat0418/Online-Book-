import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { firstValueFrom } from 'rxjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router, private userService: UserService) { }

// Lifecycle hook that runs when the component is initialized
  ngOnInit() {
    const isLoggedIn = sessionStorage.getItem('isLoggedIn') || false;
      // If the user is already logged in, navigate to the homepage component
    if(isLoggedIn) this.router.navigate(['/homepage']);
  }

  // Asynchronous login function to handle user login
  async login() {
      try {
        // Call the login method from the UserService and wait for the response using firstValueFrom
        let res: any = await firstValueFrom(this.userService.login(this.username, this.password))

        // Check if the response contains a valid userId
        if(res.userId) {
        sessionStorage.setItem("isLoggedIn", "true");
        sessionStorage.setItem("userId", res.userId);
        this.router.navigate(['']); // Redirect to the homepage component
        } else {
          throw new Error("Invalid credentails")
        }
      } catch(e) {
        console.log(e)
        alert('Invalid credentials');  // Show error message if invalid credentails are entered
      }
}
}
