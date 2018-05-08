import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {
  private baseUrl = 'http://localhost:8080/kwetter/';

	constructor(private http: Http) {
	}

	register(firstname: string, lastname: string, email: string, password: string) {
		let params = 'firstname=' + firstname + '&lastname=' +
			lastname + '&email=' + email + '&password=' + password;

		let headers = this.getHeaders();
		headers.append('content-type',
			'application/x-www-form-urlencoded');

		return this.http.post(this.baseUrl + 'users/register', params,
			{headers: headers}).toPromise();
	}

	private getHeaders() {
		let headers = new Headers();
		headers.append('Accept', 'application/json');
		return headers;
	}
}
