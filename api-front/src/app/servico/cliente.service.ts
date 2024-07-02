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

    //Método para selecionar todos os clientes
    selecionar():Observable<Cliente[]>{
      return this.http.get<Cliente[]>(this.url)
    }

    //Método para registrar clientes
    registrar(cliente:Cliente):Observable<Cliente>{
      return this.http.post<Cliente>(this.url, cliente);
    }

    //Método para editar clientes
    editar(cliente:Cliente):Observable<Cliente>{
      return this.http.put<Cliente>(this.url, cliente);
    }

    //Método para remover clientes
    remover(id:number):Observable<void>{
      return this.http.delete<void>(this.url + "/" + id);
    }
}
