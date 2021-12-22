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
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { FormsModule } from '@angular/forms';
import { RolesListComponent } from './components/roles-list/roles-list.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ChangePasswordComponent } from './change-password/change-password.component'; 
import { LoginComponent } from './components/login/login.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ManagerDashboardComponent } from './components/manager-dashboard/manager-dashboard.component';
import { AuthGuard } from './auth.guard';
import { JwtTokenService } from './services/jwt-token.service';
import { JwtInterceptorInterceptor } from './interceptor/jwt-interceptor.interceptor';
import { CreateTripComponent } from './components/create-trip/create-trip.component';
import { EditTripComponent } from './components/edit-trip/edit-trip.component';
import { EditFlightComponent } from './components/edit-flight/edit-flight.component';
import { CreateFlightComponent } from './components/create-flight/create-flight.component';
import { TripsListComponent } from './components/trips-list/trips-list.component';
import { FlightsListComponent } from './components/flights-list/flights-list.component';
import { RoleGuard } from './role.guard';
import { UserTripListComponent } from './components/user-trip-list/user-trip-list.component';
import { UserSidenavComponent } from './components/user-sidenav/user-sidenav.component';
import { MatSelectModule} from '@angular/material/select';

import { AST } from '@angular/compiler';

const routes: Routes = [
  {path: 'role/:id', component: UserListComponent, canActivate: [AuthGuard]},
  {path: 'users/:id', component: UserListComponent, canActivate: [AuthGuard, RoleGuard],  data: {role: 'ADMIN' }},
  {path: 'roles', component: RolesListComponent, canActivate: [AuthGuard]},
  {path: 'users', component: ManagerDashboardComponent, canActivate: [AuthGuard, RoleGuard],  data: {role: 'ADMIN' }},
  {path: 'create-user', component: CreateUserComponent, canActivate: [AuthGuard, RoleGuard],  data: {role: 'ADMIN' }},
  {path: 'edit-user/:id', component: EditUserComponent, canActivate: [AuthGuard] },
  {path: 'login', component: LoginComponent , canActivate: [AuthGuard]},
  {path: 'trips', component: UserDashboardComponent},
  {path: 'flights', component: FlightsListComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ADMIN' }},
  {path: 'flights/create-flight', component: CreateFlightComponent, canActivate: [AuthGuard], data: {role: 'ADMIN' }},
  {path: 'trips/create-trip', component: CreateTripComponent, canActivate: [AuthGuard], data: {role: 'USER' }},
  {path: 'trips/:id', component: EditTripComponent, canActivate: [AuthGuard]},
  {path: 'admin/trips', component: TripsListComponent, canActivate: [AuthGuard, RoleGuard], data: {role: 'ADMIN' }},
  {path: 'flights/:id', component: EditFlightComponent, canActivate: [AuthGuard], data: {role: 'ADMIN' }},

  
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  //{path: '**',redirectTo: 'create-user', pathMatch: 'full'},
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
    LoginComponent,
    ChangePasswordComponent,
    UserDashboardComponent,
    ManagerDashboardComponent,
    CreateTripComponent,
    EditTripComponent,
    EditFlightComponent,
    CreateFlightComponent,
    TripsListComponent,
    FlightsListComponent,
    UserTripListComponent,
    UserSidenavComponent,

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
    RouterModule.forRoot(routes),
    MatSelectModule
  ],
  exports: [
    RouterModule,
  ],

  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
