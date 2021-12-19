import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { Role } from '../common/role';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogin = false;
    
  roleAs: string;

  constructor() { }

  private user: User;

    isAuthorized() {
        return !!this.user;
    }

    hasRole(role: Role) {
      if(role.role_name === "ADMIN")
        return this.isAuthorized() && this.user.roleId == 1;
       else
       return this.isAuthorized() && this.user.roleId == 2;
    }

    login(role: Role) {
      //this.user = { role: role };
    }

    logout() {
      this.user = null;
    }

  getRole() {
    this.roleAs = localStorage.getItem('ROLE');
    return this.roleAs;
  }
}
