import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-adm',
  templateUrl: './adm.component.html',
  styleUrls: ['./adm.component.css']
})
export class AdmComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  redirecionaPagina(urlPage: string) {
    this.router.navigate([urlPage]);
  }

  logout() {
    localStorage.removeItem('token');
    this.redirecionaPagina('login');
  }

  public msgToast(texto: string) {
    let msg = document.getElementById('msgAdm');
    msg.onclick = (1 , eval)('(function(event){' + "M.toast({html: '" + texto + "'})" + '})');
    msg.click();
  }

}
