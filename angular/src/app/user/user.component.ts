import { Component ,OnInit, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  encapsulation: ViewEncapsulation.Emulated
})
export class UserComponent {
  username: string = '';
  password: string = '';


  constructor(private router: Router) { }

  signIn() {
    // Implement your custom validation logic here
    if (this.password && this.password.length >= 9) {
      // Perform additional validation if needed
  
      this.router.navigate(['/homepage']); // Redirect to the homepage component
    } else {
      alert('Password must be at least 9 characters'); // Show error message
    }
  }
}
