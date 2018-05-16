import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {CookieService} from 'ngx-cookie-service';
import 'rxjs/add/operator/toPromise';
import {Owner} from "../kweet/owner";

@Injectable()
export class AuthService {
	private baseUrl = 'http://localhost:8080/kwetter/rest/';

	constructor(private http: HttpClient, private cookieService: CookieService) {
	}

	authenticate(username: string, password: string, expires: boolean) {

		let headers = this.getHeaders();
    headers.append('Access-Control-Allow-Methods', 'GET, POST, PUT, OPTIONS');
    headers.append("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");



    return this.http.post<HttpResponse<Owner>>(this.baseUrl + 'user/login', { "username" : username, "password" : password },
    	{headers: headers, observe: 'response'});
  }

	isAuthenticated(): boolean {
		return !!this.cookieService.get('Auth');
	}

	getUserName(): string
  {
    return this.cookieService.get('User');
  }

	setAuthKey(key: string, username: string) {
		this.cookieService.set('Auth', key);
		this.cookieService.set('User', username);
	}

	logOut() {
		let headers = this.getHeaders();
		headers.append('Auth', this.cookieService.get('Auth'));
		this.removeAuthKey();
		return this.http.post(this.baseUrl + 'user/logout', null,
			{headers: headers}).toPromise();
	}

	removeAuthKey() {
		this.cookieService.delete('Auth');
		this.cookieService.delete('User');
	}

	private getHeaders() {
		let headers = new HttpHeaders();
		headers.append('Accept', 'application/json');
		return headers;
	}
}
