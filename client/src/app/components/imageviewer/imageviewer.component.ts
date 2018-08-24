import { Component, AfterViewInit, ViewChild, ElementRef, HostListener, OnInit, Input } from '@angular/core';
import { IMAGEVIEWER_CONFIG, ImageViewerConfig } from '@hallysonh/ngx-imageviewer';

const IMAGEVIEWER_CUSTOM_CONFIG: ImageViewerConfig = {
  width: 200, // component default width
  height: 200, // component default height
  bgStyle: '#ffffff', // component background style
  buttonStyle: {
  bgStyle: '#80CBC4', //  buttons' background style
  iconStyle: '#ffffff', // buttons' icon colors
  borderStyle: '#ffffff', // buttons' border style
  borderWidth: 2 // buttons' border width (0 == disabled)
}
}

@Component({
  selector: 'app-imageviewer',
  templateUrl: './imageviewer.component.html',
  styleUrls: ['./imageviewer.component.scss'],
  providers: [
    {
      provide: IMAGEVIEWER_CONFIG,
      useValue: IMAGEVIEWER_CUSTOM_CONFIG
    }
  ]
})
export class ImageViewerComponent implements AfterViewInit {
  @ViewChild('imagewrapper') wrapper: ElementRef;

  private _canvasDim = { width: 10, height: 10 };

  @Input()
  urlSrc: string

  get canvasDim() {
    return this._canvasDim;
  }

  ngAfterViewInit() {
    this.updateCanvasDim();
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.updateCanvasDim();
  }

  private updateCanvasDim() {
    const el = this.wrapper && this.wrapper.nativeElement ? this.wrapper.nativeElement : null;
    if (el && (el.offsetWidth !== this._canvasDim.width || el.offsetHeight !== this._canvasDim.height)) {
      const newDim = { width: el.offsetWidth - 2, height: el.offsetHeight - 2 };
      setTimeout(() => this._canvasDim = newDim, 0);
    }
  }
}
