import { User } from './User';

export interface Message {
  id?: number;
  title: string;
  content: string;
  principal?: User;
  recipient?: User;
  addressees?: User[];
}
