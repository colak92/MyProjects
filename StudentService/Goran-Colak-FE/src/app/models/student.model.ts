import { City } from "./city.model";

export class Student {
  id?: number;
  indexnumber?: string;
  indexyear?: number;
  firstname?: string;
  lastname?: string;
  jmbg?: bigint;
  email: string;
  address: string;
  city?: City;
  cityId?: number;
  cityName?: string;
  currentyearofstudy?: number;
}