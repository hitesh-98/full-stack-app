import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  credentials = {
    email: '',
    password: '',
  };

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  onSubmit() {
    if (
      this.credentials.email != '' &&
      this.credentials.password != '' &&
      this.credentials.email != null &&
      this.credentials.password != null
    ) {
      console.log('Form submitted');

      //generate token
      this.loginService.generateToken(this.credentials).subscribe(
        (res: any) => {
          console.log(res);
          this.loginService.loginUser(res.token);
          window.location.href = "/dashboard";
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
