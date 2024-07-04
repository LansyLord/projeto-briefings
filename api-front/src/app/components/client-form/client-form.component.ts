import { Component } from '@angular/core';
import { Cliente } from 'src/app/models/Cliente';
import { PrincipalComponent } from '../principal/principal.component';
import { Briefing } from 'src/app/models/Briefing';
import { ClienteService } from 'src/app/servico/cliente.service';
import { BriefingService } from 'src/app/servico/briefing.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent {

  btnVoltar: boolean = true;

  cliente: Cliente = new Cliente();

  tabelaClientes: boolean = true;

  // JSON de Briefings
  briefings: Briefing[] = this.principal.briefings;

  // JSON de Clientes
  clientes: Cliente[] = this.principal.clientes;

  constructor(
    private principal: PrincipalComponent,
    private servicoBriefing: BriefingService,
    private servicoCliente: ClienteService
  ) { }


  voltar(): void {
    this.principal.voltar();
    this.btnVoltar = false;
  }

  //Método de seleção de clientes
  selecionarClientes(): void {
    this.servicoCliente.selecionar()
      .subscribe(retorno => this.clientes = retorno);
  }

  // Método para selecionar um cliente específica
  selecionarCliente(index: any) {

    this.cliente = this.clientes[index];
    this.tabelaClientes = false;
    
  }
}
