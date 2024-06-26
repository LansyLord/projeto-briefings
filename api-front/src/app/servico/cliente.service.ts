import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../models/Cliente';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

    //Url da APi
    private url:string ='http://localhost:8080/api/cliente';

    //Construtor
    constructor(private http:HttpClient) { }

    //Método para selecionar todas as briefings
    selecionar():Observable<Cliente[]>{
      return this.http.get<Cliente[]>(this.url)
    }
}
