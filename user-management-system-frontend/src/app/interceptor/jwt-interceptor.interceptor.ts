import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
        const cloned = request.clone({
          headers: request.headers.set("Authorization", "Bearer " + token),
          
        });

        return next.handle(cloned).pipe(
          catchError((error: HttpErrorResponse) => {
              let errorMsg = '';
              if (error.error instanceof ErrorEvent) {
                  console.log('This is client side error');
                  errorMsg = `Error: ${error.error.message}`;
              } else {
                  console.log('This is server side error');
                  errorMsg = `Error Code: ${error.status},  Message: ${error.error.message}`;
              }
              window.alert(error.error.message)
              return throwError(errorMsg);
          })
      );
    }
    
  
}
