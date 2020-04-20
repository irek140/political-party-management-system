import { User } from './User';

export interface Vote {
  id?: number;
  user_id?: number;
  voted?: boolean;
}
