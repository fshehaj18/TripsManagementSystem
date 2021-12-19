import { Component, OnInit } from '@angular/core';
import { LogInCredentials } from 'src/app/common/log-in-credentials';
import { JwtTokenService } from 'src/app/services/jwt-token.service';
import { UsersService } from 'src/app/services/users.service';
import {NgForm} from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  response: any;

  credentials: LogInCredentials = new LogInCredentials();

  constructor(private service: JwtTokenService, private userService: UsersService, private router: Router) { }

  ngOnInit(): void {
    //this.getAccessToken(this.authRequest);
  }

  
  onSubmit(){
    
    let response = this.service.generateToken(JSON.parse(JSON.stringify(this.credentials)));
    console.log(response);
    response.subscribe(
      res => {
        let tokenValue = JSON.parse(res).jwt;
        localStorage.setItem('token', tokenValue);

        let role = JSON.parse(res).role;
        localStorage.setItem('role', role);
        console.log(role);
        if(role === "ADMIN")
        this.router.navigate(['/users']);
        else if(role === "USER")
        this.router.navigate(['/trips']);
      },
      //err => console.log(err)
    )
  }
  

  
  accessApi(token): void {
    //this.service.welcome(token);
    this.router.navigate(['users']);
  }

}
