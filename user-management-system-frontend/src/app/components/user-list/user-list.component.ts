import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faEdit, faPlane, faPlus } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { Role } from 'src/app/common/role';
import { User } from 'src/app/common/user';
import { UsersService } from 'src/app/services/users.service';
import { CreateUserComponent } from '../create-user/create-user.component';
import { EditUserComponent } from '../edit-user/edit-user.component';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  faEdit = faEdit;
  faTrash = faTrash;
  faPlus = faPlus;
  faFlight = faPlane;

  user: User[] = [];
  currentRoleId: number | undefined;
  private createUser: CreateUserComponent;

  constructor(private userService: UsersService, private route:ActivatedRoute, private routes: Router ) { 

  }

  ngOnInit(): void {
      this.listUsers();
    
}
  listUsers(){

   /* const hasRoleId: boolean = this.route.snapshot.paramMap.has('id');

    if(hasRoleId && this.route.snapshot.paramMap.get('id') != null){
      this.currentRoleId = Number(this.route.snapshot.paramMap.get('id'));
    }
    else{
      this.currentRoleId = 1;
    };*/
    this.userService.getUsersList().subscribe(
      (data: User[]) =>{
       this.user = data;
      
      }
    );
  }
  updateUser(id: number)
  {
    console.log(id);
    this.routes.navigate(['edit-user', id]);  }

    deleteUser(id: number)
  {
    console.log(id);
    this.userService.deleteUser(id).subscribe(data => {
      this.listUsers();
    });
  }

  getMessage(): boolean{
    return this.createUser.message;
  }
}
