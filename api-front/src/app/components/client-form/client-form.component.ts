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

  tituloCadastro: boolean = true;

  tituloEdicao: boolean = false;

  btnCadastro: boolean = true;

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
    this.btnCadastro = false;
    this.btnVoltar = false;
  }

  // Método de registro de clientes
  registrarCliente(): void {
    if(!this.cliente.email.includes("@") && (!this.cliente.email.includes("."))){
      alert("O email inserido é inválido!");
      this.cliente.email = "";
      return;
    }

    this.servicoCliente.registrar(this.cliente)
      .subscribe(retorno => {
        // Registrar cliente
        this.clientes.push(retorno);

        // Limpar formulário
        this.cliente = new Cliente();

        // Mensagem
        alert("Cliente cadastrado com sucesso");

      });
  }
  //Método para edição de clientes
  editarCliente(): void {
    this.servicoCliente.editar(this.cliente)
      .subscribe(retorno => {

        //Obter posição no vetor onde está o cliente
        let posicao = this.clientes.findIndex(obj => {
          return obj.id == retorno.id;
        });

        this.clientes[posicao] = retorno;

        // Variável para visibilidade do título do formulário de cadastro
        this.tituloCadastro = true;

        // Variável para visibilidade do título do formulário de edição
        this.tituloEdicao = false;

        // Limpar formulário
        this.cliente = new Cliente();

        this.btnCadastro = true;

        this.tabelaClientes = true;

        alert("As alterações da briefing foram salvas");

      });
  }

    // Método para cancelar edição
    cancelarEdicao(): void {

      this.cliente = new Cliente();

      this.btnCadastro = true;

      this.tabelaClientes = true;

      this.btnVoltar = true;

      this.selecionarClientes();

    }

     // Método para remoção de briefings
  removerCliente(index: number): void {

    this.cliente = this.clientes[index]

    const confirmation = confirm("Tem certeza que deseja apagar este cliente?");

    if(confirmation){
      this.servicoCliente.remover(this.cliente.id)
      .subscribe(retorno => {

        // Remover briefing do vetor
        this.clientes.splice(this.cliente.id, 1);

        this.selecionarClientes();
      });
    }
    // Limpar formulário
    this.cliente = new Cliente();
  }
}
