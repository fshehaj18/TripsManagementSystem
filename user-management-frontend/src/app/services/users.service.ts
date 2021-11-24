import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import {map} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class UsersService {
  

  private roleId!: number;

  private baseUrl='http://localhost:8080/api/users';


  constructor(private httpClient: HttpClient) { }

  getUsersList(): Observable<User[]>{

    return this.httpClient.get<User[]>(`${this.baseUrl}`);
    
  }

  getUserById(id: number): Observable<User>{
    
    return this.httpClient.get<User>(`${this.baseUrl}/${id}`);

  }
  
  createUser(user: User): Observable<Object>
  {

    let addUrl=`http://localhost:8080/api/role/${user.role_id}/users`;

      return this.httpClient.post(`${addUrl}`, user);
   
  }

  
  

  editUser(id: number, user: User): Observable<Object>
  {
    
    let editUrl=`http://localhost:8080/api/role/${user.role_id}/users/${id}`;
console.log(editUrl);
    return this.httpClient.put(`${editUrl}`, user);
  }

  deleteUser(id: number): Observable<Object>
  {
    let deleteUrl=`http://localhost:8080/api/users/${id}`;

    return this.httpClient.delete(deleteUrl);
  }
}

