import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Briefing } from '../models/Briefing';

@Injectable({
  providedIn: 'root'
})
export class BriefingService {

    //Url da APi
    private url:string ='http://localhost:8080/api/briefing';

    //Construtor
    constructor(private http:HttpClient) { }

    //Método para selecionar todas as briefings
    selecionar():Observable<Briefing[]>{
      return this.http.get<Briefing[]>(this.url)
    }

    //Método para registrar briefings
    registrar(briefing:Briefing):Observable<Briefing>{
      return this.http.post<Briefing>(this.url, briefing);
    }

    //Método para editar briefings
    editar(briefing:Briefing):Observable<Briefing>{
      return this.http.put<Briefing>(this.url, briefing);
    }

    //Método para remover briefings
    remover(id:number):Observable<void>{
      return this.http.delete<void>(this.url + "/" + id);
    }
}
