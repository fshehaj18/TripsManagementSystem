import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user:User = new User();
  public message: boolean = false;

  constructor(private userService: UsersService, private router: Router) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.userService.createUser(this.user).subscribe( data => {

      console.log(data);
      this.message = true;
    },
    err => {/*console.log(err)*/}
  
    );
  
  }

  goToUsersList(){
    
    this.router.navigate(['/users']);
  }
  onSubmit(): void {
    this.saveUser();
    
  }
}
