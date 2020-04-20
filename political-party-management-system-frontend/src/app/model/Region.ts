import { Constituency } from './Constituency';

export interface Region {
  id?: number;
  name: string;
  constituencies?: Constituency[];
}
