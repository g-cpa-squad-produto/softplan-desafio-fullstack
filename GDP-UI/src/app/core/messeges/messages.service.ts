import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

const toastConfig = {
    closeButton: true
};

@Injectable({ providedIn: 'root'})
export class MassegesService {
    constructor (private toastr: ToastrService) {
    }

    public success (messege, title?) {
        if (!title) {
            this.toastr.success( messege, 'Success!' , toastConfig);
        } else {
            this.toastr.success(messege, title, toastConfig);
        }
    }

    public error (messege, title?) {
        if (!title) {
            this.toastr.error( messege, 'Error!', toastConfig);
        } else {
            this.toastr.error(messege, title, toastConfig);
        }
    }
 }
