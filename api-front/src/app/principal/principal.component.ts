import { Briefing } from './../models/Briefing';
import { Component } from '@angular/core';
import { BriefingService } from '../servico/briefing.service';
import { Cliente } from '../models/Cliente';
import { ClienteService } from '../servico/cliente.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent {

  //Variável para visibilidade dos botões
  btnCadastro:boolean = true;

  briefing = new Briefing();

  //JSON de Briefings
  briefings:Briefing[] = [];

  //JSON de Clientes
  clientes:Cliente[] = [];

  //Construtor
  constructor(
    private servicoBriefing:BriefingService,
    private servicoCliente:ClienteService){}

  //Método de seleção de briefings
  selecionarBriefings():void{
    this.servicoBriefing.selecionar()
    .subscribe(retorno => this.briefings = retorno);
  }

  //Método de registro de briefings
  registrarBriefing():void{
    this.servicoBriefing.registrar(this.briefing)
    .subscribe(retorno => { this.briefings.push(retorno); });
  }

  //Método de seleção de clientes
  selecionarClientes():void{
    this.servicoCliente.selecionar()
    .subscribe(retorno => this.clientes = retorno);
  }

  //Método de inicialização
  ngOnInit(){
    this.selecionarBriefings();
    this.selecionarClientes();
  }

}
