import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LegalAdvice } from './legal.advice.model';
import envConf from '../../shared/env-config'

const URL = `${envConf.api.url}/api/v1/legal-advice`

@Injectable({
  providedIn: 'root'
})
export class LegalAdviceService {

  constructor(
    private http: HttpClient
  ) { }

  getAllLegalAdvices(){
    console.log('getAllLegalAdvices URL=>', URL);
    return this.http.get<LegalAdvice[]>(`${URL}`);
  }

  getLegalAdviceById(id){
    console.log('getLegalAdviceById URL=>', URL);
    return this.http.get<LegalAdvice>(`${URL}/${id}`);
  }

  createLegalAdvice(legalAdvice) {
    console.log('legalAdvice=>', legalAdvice);
    console.log('createLegalAdvice URL=>', URL);
    return this.http.post(`${URL}`, legalAdvice);
  }

  updateLegalAdvice(id, legalAdvice) {
    console.log('legalAdvice=>', legalAdvice);
    console.log('updateLegalAdvice URL=>', `${URL}/${id}`);
    return this.http.post(`${URL}/${id}`, legalAdvice);
  }
  
}
