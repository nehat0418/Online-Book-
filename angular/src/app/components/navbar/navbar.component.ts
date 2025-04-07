import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  links = [
    { path: '/home', title: 'Home' },
    { path: '/books', title: 'Books' },
    { path: '/cart', title: 'Cart' },
    { path: '/login', title: 'Login' },
  ];

  navbarShown = true;
  isLoggedIn = false;

  constructor(private router: Router) {}

  ngOnInit(): any {
    this.router.events.subscribe(e=>{
      if(e instanceof NavigationEnd){
        this.navbarShown = e.url !== "/login" && e.url !== '/signup';
        this.isLoggedIn = !!sessionStorage.getItem("isLoggedIn");
        if(this.isLoggedIn) {
          this.links[this.links.length - 1].title = "Logout"
          if(this.links.length === 5) return;
          this.links.splice(3, 0, {
            path: "/purchase",
            title: "Purchases"
          })
        }
      }
    })
  }

  navigateToPath(path: string) {
    if(path === "/login" && this.isLoggedIn) {
      sessionStorage.removeItem('isLoggedIn');
      sessionStorage.removeItem('userId');
    }
    this.router.navigate([path]);
  }
}
