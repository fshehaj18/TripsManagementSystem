import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  id = -1;
  user:User = new User();

  constructor(private userService: UsersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    

    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    }, error => console.log(error));
  }

  onSubmit(): void {
    this.userService.editUser(this.id, this.user).subscribe( data =>{
      console.log(data);
    }
    , error => console.log(error));this.goToUsersList();
  }
   
  goToUsersList(){
    this.router.navigate(['/users']);
  }
}
