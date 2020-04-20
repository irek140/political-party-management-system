import { User } from './User';

export class Article {
  id?: number;
  title: string;
  content: string;
  author?: User;
}
