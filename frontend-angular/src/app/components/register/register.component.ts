import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  credentials = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
  };

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    if (
      this.credentials.email != '' &&
      this.credentials.password != '' &&
      this.credentials.confirmPassword != '' &&
      this.credentials.firstName != '' &&
      this.credentials.lastName != '' &&
      this.credentials.email != null &&
      this.credentials.password != null &&
      this.credentials.password != null &&
      this.credentials.firstName != null &&
      this.credentials.lastName != null
    ) {
      console.log('Form submitted');

      const registerCredentials: any = {
        email: this.credentials.email,
        password: this.credentials.password,
        firstName: this.credentials.firstName,
        lastName: this.credentials.lastName,
      };

      this.registerService.registerUser(registerCredentials).subscribe(
        (res: any) => {
          console.log(res);
          this.router.navigate(['/login']);
        },
        (err: any) => {
          console.log(err);
        }
      );
    } else {
      console.log('Fields are empty');
    }
  }
}
