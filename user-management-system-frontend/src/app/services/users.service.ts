import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import {map} from 'rxjs/operators';
import { LogInCredentials } from '../common/log-in-credentials';
@Injectable({
  providedIn: 'root'
})
export class UsersService {
  

  private roleId!: number;

  private baseUrl='http://localhost:8080/admin/users';


  constructor(private httpClient: HttpClient) { }

  getUsersList() {

    return this.httpClient.get(`${this.baseUrl}`).pipe(
      map((res: any) => 
      res.map((data) => {
        
        return{
            id: data.id,
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            roleId: data.role.roleId,
        };
      })
      )
    );
    
  }

  getUserById(id: number): Observable<User>{
    
    return this.httpClient.get(`${this.baseUrl}/${id}`).pipe(
      map((data: any) =>{
        return {
          id: data.id,
          firstName: data.firstName,
            lastName: data.lastName,
            password: null,
            email: data.email,
            roleId: data.role.roleId,
        }
        })
    
    );

  }
  
  createUser(user: User): Observable<Object>
  {

    let addUrl=`http://localhost:8080/api/users`;

      return this.httpClient.post(`${addUrl}`, user);
   
  }


  getCredentials(login: LogInCredentials){
    
  }

  
  

  editUser(id: number, user: User): Observable<Object>
  {
    
    let editUrl=`http://localhost:8080/admin/user/${id}`;
console.log(editUrl);
    return this.httpClient.patch(`${editUrl}`, user);
  }

  deleteUser(id: number): Observable<Object>
  {
    let deleteUrl=`http://localhost:8080/admin/users/${id}`;

    return this.httpClient.delete(deleteUrl);
  }

  getRole(user: User): number{
      return user.roleId;
  }
}

