import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { HeaderComponent } from './components/header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import {MatSidenavModule} from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { FormsModule } from '@angular/forms';
import { RolesListComponent } from './components/roles-list/roles-list.component';
import { EditUserComponent } from './components/edit-user/edit-user.component'; 
const routes: Routes = [
  {path: 'role/:id', component: UserListComponent},
  {path: 'users/:id', component: UserListComponent},
  {path: 'roles', component: RolesListComponent},
  {path: 'users', component: UserListComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: 'edit-user/:id', component: EditUserComponent },
  
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: '**',redirectTo: 'users', pathMatch: 'full'},
]
@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    UserListComponent,
    HeaderComponent,
    CreateUserComponent,
    RolesListComponent,
    EditUserComponent,

  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    HttpClientModule,
    MatDividerModule,
    MatListModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
