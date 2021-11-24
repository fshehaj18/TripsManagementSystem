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
  constructor(private userService: UsersService, private router: ActivatedRoute) { }

  ngOnInit(): void {

    this.id = this.router.snapshot.params['id'];
    

    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    }, error => console.log(error));
  }

  onSubmit(): void {
    this.userService.editUser(this.id, this.user);

  }

}
