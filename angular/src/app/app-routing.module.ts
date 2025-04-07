import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CartComponent } from './cart/cart.component';
import { BookComponent } from './book/book.component';
import { SignupComponent } from './signup/signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { PaymentComponent } from './payment/payment.component';
import { PurchasesComponent } from './purchases/purchases.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: HomepageComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
  { path: 'books', 
  component: BookComponent },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'signup',
    component: SignupComponent,},
    {
      path:'',
      redirectTo:'/login',
      pathMatch:'full',
  },
  {
    path: 'forgot-password',
    component: ForgotPasswordComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  
  },
  { 
    path: 'payment',
   component: PaymentComponent,
   },
   {
    path: "purchase",
    component: PurchasesComponent
   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
