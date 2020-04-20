import { User } from '../User';
import { Vote } from '../Vote';

export interface Survey4 {
  id?: number;
  question: string;
  answer1: string;
  answer2: string;
  answer3: string;
  answer4: string;
  value1?: number;
  value2?: number;
  value3?: number;
  value4?: number;
  region?: string;
  constituency?: string;
  owner?: User;
  addressees?: User[];
  votes?: Vote[];
  created?: Date;
  enddate?: Date;
}
