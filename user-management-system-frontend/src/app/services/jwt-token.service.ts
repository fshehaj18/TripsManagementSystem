import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JwtTokenService {

  constructor(private httpClient: HttpClient, private router: Router) { }

   generateToken(request){
     if(localStorage.getItem('token'))
    localStorage.removeItem('token');
    return this.httpClient.post<any>("http://localhost:8080/login", request, {responseType:'text' as 'json'});
    
  }
  

  getToken(){
    return localStorage.getItem('token');
  }


  loggedIn(){
    return !!localStorage.getItem('token');
  }

  
  

  /*public welcome(token){
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    console.log(tokenStr);
    localStorage.setItem('token', tokenStr);
    this.router.navigate(['/users']);
      //return this.httpClient.get('http://localhost:8080/api/users', {headers, responseType:'text' as 'json'});
  }*/
}
