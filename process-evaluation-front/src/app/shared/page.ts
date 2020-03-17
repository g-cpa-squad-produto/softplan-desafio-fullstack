export class Page<T> {
  content: T[];
  totalElements: number;
  last: boolean;
  totalPages: number;
  size: number;
  number: number;
  sort: any;
  numberOfElements: number;
  first: boolean;
  empty: false;
}