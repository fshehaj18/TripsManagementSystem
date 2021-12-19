import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Role } from 'src/app/common/role';
import { RolesService } from 'src/app/services/roles.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.css']
})
export class RolesListComponent implements OnInit {

  role: Role[] = [];
  
  constructor(private roleService: RolesService, private route:ActivatedRoute) { 

  }

  
  

  ngOnInit(): void {
   this.getRoles();
}
  private getRoles(){

   /* const hasRoleId: boolean = this.route.snapshot.paramMap.has('id');

    if(hasRoleId && this.route.snapshot.paramMap.get('id') != null){
      this.currentRoleId = Number(this.route.snapshot.paramMap.get('id'));
    }
    else{
      this.currentRoleId = 1;
    };*/
    this.roleService.getRolesList().subscribe(
      (data: Role[]) =>{
        console.warn(data);
       this.role = data;
      
      }
    );
    console.log(this.role);
  }
}
