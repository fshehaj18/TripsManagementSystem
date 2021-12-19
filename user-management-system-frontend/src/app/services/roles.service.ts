import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role } from '../common/role';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class RolesService {

  private baseUrl='http://localhost:8080/api/roles';

  constructor(private httpClient: HttpClient) { }

  getRolesList(/*theRoleId: number*/): Observable<Role[]>{
    /*const searchUrl = `${this.baseUrl}/search/findByRoleRoleId?roleId=${theRoleId}`;
    console.log(searchUrl);*/
    return this.httpClient.get<Role[]>(`${this.baseUrl}`);
    

    

  }
  
  
}