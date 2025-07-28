export interface Planet {
  name: string;
  climate: string;
  created: string;
  diameter: string;
  edited: string;
  films: string[];
  gravity: string;
  orbitalPeriod: string;
  population: string;
  residents: string[];
  rotationPeriod: string;
  surfaceWater: string;
  terrain: string;
  url: string;
}

export interface PlanetResponse {
  results: Planet[];
  count: number;
}