import { Injectable } from '@angular/core';

@Injectable()
export class MaskHelperService {
  constructor() {}

  /**
   * Block non digits
   */
  blockNonDigits(event: any) {
    const value = this.onlyDigitsValues(event);
    event.target.value = value;
  }

  /**
   * Return if is CPF or CNPJ
   */
  checkDocumentType(event: any) {
    const value = this.onlyDigitsValues(event);
    if (value.length > 11) { return '00.000.000/0000-00'; }
    return '000.000.000-00';
  }

  /**
   * Return only digits values
   */
  onlyDigitsValues(event: any) {
    return event.target.value.replace(/\D/g, '');
  }

}
