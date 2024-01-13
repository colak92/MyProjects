import { Subject } from 'src/app/models/subject.model';
import { City } from './city.model';
import { Title } from './title.model';

export class Professor {
  id?: number;
  firstname?: string;
  lastname?: string;
  email: string;
  address: string;
  phone: string;
  reelectiondate?: Date;
  city?: City;
  cityId?: number;
  cityName?: string;
  title?: Title;
  titleId?: number;
  titleName?: string;
  subjectList: Subject[];
}