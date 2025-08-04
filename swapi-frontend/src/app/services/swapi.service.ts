import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Planet } from '../models/planet.model';
import { Person } from '../models/person.model';
import { SWAPIResponse } from '../models/swapiresponse.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SwapiService {
  private readonly baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  getPeople(search?: string, sortBy?: string, direction?: string): Observable<SWAPIResponse<Person[]>> {
    let params = new HttpParams();
    if (search) params = params.set('search', search);
    if (sortBy) params = params.set('sortBy', sortBy);
    if (direction) params = params.set('direction', direction);
    console.log('Fetching people from service ', this.baseUrl, ' with params:', params.toString());
    return this.http.get<SWAPIResponse<Person[]>>(`${this.baseUrl}/people`, { params });
  }

  getPlanets(search?: string, sortBy?: string, direction?: string): Observable<SWAPIResponse<Planet[]>> {
    let params = new HttpParams();
    if (search) params = params.set('search', search);
    if (sortBy) params = params.set('sortBy', sortBy);
    if (direction) params = params.set('direction', direction);
    console.log('Fetching planets from service ', this.baseUrl, ' with params:', params.toString());
    return this.http.get<SWAPIResponse<Planet[]>>(`${this.baseUrl}/planets`, { params });
  }

}
