export interface Person {
  name: string;
  birth_year: string
  eye_color: string;
  gender: string;
  hair_color: string;
  height: string;
  homeworld: string;
  mass: string;
  skin_color: string;
  created: string;
  edited: string;
  films: string[];
  species: string[];
  starships: string[];
  url: string;
  vehicles: string[];
}

export interface PersonResponse {
  results: Person[];
  count: number;
}