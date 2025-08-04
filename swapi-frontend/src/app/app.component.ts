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

  visibleColumns: { [key: string]: boolean } = {};

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

      type PersonKey = keyof Person;

      const fields: PersonKey[] = [
        'birth_year', 'eye_color', 'gender', 'hair_color', 'height',
        'homeworld', 'mass', 'skin_color', 'created', 'edited',
        'films', 'species', 'starships', 'url', 'vehicles'
      ];

      this.visibleColumns = {};
      for (const field of fields) {
        const valueExists = this.people.some(p => {
          const value = p[field];
          return Array.isArray(value) ? value.length > 0 : value !== null && value !== '';
        });
        this.visibleColumns[field] = valueExists;
      }
    });
  }

  getPlanets(): void {
    this.swapiService.getPlanets(this.planetsSearch, this.planetsSortBy, this.planetsDirection)
    .subscribe((response: any) => {
      console.log('Response from service (planets):', response);
      this.planets = response.result;

      type PlanetKey = keyof Planet;

      const fields: PlanetKey[] = [
        'name', 'climate', 'created', 'diameter', 'edited',
        'films', 'gravity', 'orbitalPeriod', 'population', 'residents',
        'rotationPeriod', 'surfaceWater', 'terrain', 'url'
      ];

      this.visibleColumns = {};
      for (const field of fields) {
        const valueExists = this.planets.some(p => {
          const value = p[field];
          return Array.isArray(value) ? value.length > 0 : value !== null && value !== '';
        });
        this.visibleColumns[field] = valueExists;
      }
    });
  }
}
