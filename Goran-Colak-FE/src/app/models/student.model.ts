import { City } from "./city.model";

export class Student {
  id?: number;
  indexnumber?: string;
  indexyear?: number;
  firstname?: string;
  lastname?: string;
  email: string;
  address: string;
  city?: City;
  cityId?: number;
  cityName?: string;
  currentyearofstudy?: number;
}