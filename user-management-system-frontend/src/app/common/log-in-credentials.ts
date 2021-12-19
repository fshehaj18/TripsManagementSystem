export class LogInCredentials {

     username: string;
    password: string;

    /*public constructor(username: string, password: string){
        this.username = username;
        this.password = password;
    }*/

    public constructor(){}
    
    public getUsername(){
        return this.username;
    }
    public getPassword(){
        return this.password;
    }
}
