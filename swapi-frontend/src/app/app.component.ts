import { Component, OnInit } from '@angular/core';
import { SwapiService } from './services/swapi.service';
import { Person } from './models/person.model';
import { Planet } from './models/planet.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  people: Person[] = [];
  planets: Planet[] = [];

  peopleSearch = '';
  peopleSortBy = 'name';
  peopleDirection = 'asc';

  planetsSearch = '';
  planetsSortBy = 'name';
  planetsDirection = 'asc';


  constructor(private swapiService: SwapiService) {}

  ngOnInit(): void {
    this.getPeople();
    this.getPlanets();
  }

  getPeople(): void {
    this.swapiService.getPeople(this.peopleSearch, this.peopleSortBy, this.peopleDirection)
    .subscribe((response: any) => {
      console.log('Response from service (people):', response);
      this.people = response.result;
    });
  }

  getPlanets(): void {
    this.swapiService.getPlanets(this.planetsSearch, this.planetsSortBy, this.planetsDirection)
    .subscribe((response: any) => {
      console.log('Response from service (planets):', response);
      this.planets = response.result;
    });
  }
}
