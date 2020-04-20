import { Region } from './Region';

export interface Constituency {
  id?: number;
  name: string;
  region?: Region;
  parent?: string;
}
