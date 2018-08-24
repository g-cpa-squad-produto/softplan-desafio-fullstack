import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-only-for-dev',
  templateUrl: './only-for-dev.component.html',
  styleUrls: ['./only-for-dev.component.css']
})
export class OnlyForDevComponent implements OnInit {

  zoomIndex = 1;
  imgSrc = 'https://1.bp.blogspot.com/-3-AeZEPxb74/VD_YJB2D0ZI/AAAAAAAAABU/JWQpPf9BPw8/s1600/IMG_41862524318112.jpeg';
  // './assets/img/interfile-site.png';

  constructor(
  ) { }

  ngOnInit() {

  }

  zoomMore() {
    console.log('CHAMOU ZOOM+');
    const imgTeste = document.getElementsByName('imgTeste')[0];
    this.zoomIndex++;
    imgTeste.classList.add(`zoom-beta-${this.zoomIndex}`);
  }

  zoomLess() {
    console.log('CHAMOU ZOOM-');
    const imgTeste = document.getElementsByName('imgTeste')[0];
    console.log('imgTeste', imgTeste);
    imgTeste.classList.remove(`zoom-beta-${this.zoomIndex}`);
    this.zoomIndex--;
  }

  zoom(e) {
    if (e.deltaY < 0) {
      this.zoomLess();
    } else {
      this.zoomMore();
    }
  }

}
