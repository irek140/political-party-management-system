import { User } from './User';

export interface Task {
  id?: number;
  taskTitle: string;
  taskContent: string;
  principal?: User;
  recipient?: User;
  addressees?: User[];
  inProgress?: boolean;
  done?: boolean;
  removed?: boolean;
  startdate?: Date;
  enddate?: Date;
  report?: string;
}
