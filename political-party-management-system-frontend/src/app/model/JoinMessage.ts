import { User } from './User';

export class JoinMessage {
  id?: number;
  content?: string;
  author?: User;
  isMember?: boolean;
}
