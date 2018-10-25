import { HttpHeaders } from '@angular/common/http';

export class GenericServerService {

  private url: string;

  protected httpOptions = {};

  constructor() {
    // tslint:disable-next-line:quotemark
    this.url = "http://localhost:8080";
    this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json;charset=UTF-8' })
    };
  }

  getUrl(): string {
    return this.url;
  }
}
