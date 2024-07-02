import { Component } from '@angular/core';
import { Cliente } from 'src/app/models/Cliente';
import { PrincipalComponent } from '../principal/principal.component';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent {

  btnVoltar: boolean = true;
  cliente: Cliente = new Cliente();

  constructor(private principal: PrincipalComponent){}


  voltar(): void{
    this.principal.voltar();
    this.btnVoltar = false;
  }
}
