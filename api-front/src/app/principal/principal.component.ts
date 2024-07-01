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

  // Variável para visibilidade dos botões
  btnCadastro: boolean = true;

  // Variável para visibilidade da tabela
  tabela: boolean = true;

  // Variável para visibilidade do título do formulário de cadastro
  tituloCadastro: boolean = true;

  // Variável para visibilidade do título do formulário de edição
  tituloEdicao: boolean = false;

  briefing = new Briefing();

  // JSON de Briefings
  briefings: Briefing[] = [];

  // JSON de Clientes
  clientes: Cliente[] = [];

  // Construtor
  constructor(
    private servicoBriefing: BriefingService,
    private servicoCliente: ClienteService) { }

  // Método de seleção de briefings
  selecionarBriefings(): void {
    this.servicoBriefing.selecionar()
      .subscribe(retorno => this.briefings = retorno);
  }

  // Método de registro de briefings
  registrarBriefing(): void {
    this.servicoBriefing.registrar(this.briefing)
      .subscribe(retorno => {
        // Registrar briefing
        this.briefings.push(retorno);

        // Limpar formulário
        this.briefing = new Briefing();

        // Mensagem
        alert("Briefing cadastrada com sucesso");

      });
  }

  //Método para edição de briefings
  editarBriefing(): void {
    this.servicoBriefing.editar(this.briefing)
      .subscribe(retorno => {

        //Obter posição no vetor onde está a briefing
        let posicao = this.briefings.findIndex(obj => {
          return obj.id == retorno.id;
        });

        this.briefings[posicao] = retorno;

        // Variável para visibilidade do título do formulário de cadastro
        this.tituloCadastro = true;

        // Variável para visibilidade do título do formulário de edição
        this.tituloEdicao= false;

        // Limpar formulário
        this.briefing = new Briefing();

        this.btnCadastro = true;

        this.tabela = true;

        alert("As alterações da briefing foram salvas");

      });
  }

  // Método para remoção de briefings
  removerBriefing(index: number): void {

    this.briefing = this.briefings[index]

    const confirmation = confirm("Tem certeza que deseja apagar esta briefing?");

    if(confirmation){
      this.servicoBriefing.remover(this.briefing.id)
      .subscribe(retorno => {

        // Remover briefing do vetor
        this.briefings.splice(this.briefing.id, 1);

        this.selecionarBriefings();
      });
    }
    // Limpar formulário
    this.briefing = new Briefing();
  }

  //Método de seleção de clientes
  selecionarClientes(): void {
    this.servicoCliente.selecionar()
      .subscribe(retorno => this.clientes = retorno);
  }

  // Método para selecionar uma briefing específica
  selecionarBriefing(index: any) {

    this.briefing = this.briefings[index];

    const clienteEncontrado = this.clientes.find(c => c.name === this.briefing.clienteName);
    this.briefing.cliente = clienteEncontrado ? clienteEncontrado : new Cliente(); // ou qualquer valor padrão do tipo Cliente

    // Variável para visibilidade do título do formulário de cadastro
    this.tituloCadastro = false;

    // Variável para visibilidade do título do formulário de edição
    this.tituloEdicao= true;

    this.btnCadastro = false;

    this.tabela = false;

  }

  // Método para mudar o estado da briefing
  mudarEstadoBriefing(index: number) {
    // Alternar o estado da briefing entre "APPROVED", "NEGOTIATION", e "FINISHED"
    this.briefing = this.briefings[index]

    const clienteEncontrado = this.clientes.find(c => c.name === this.briefing.clienteName);
    this.briefing.cliente = clienteEncontrado ? clienteEncontrado : new Cliente();

    switch (this.briefing.status) {
      case 'APPROVED':
        this.briefing.status = 'NEGOTIATION';
        break;
      case 'NEGOTIATION':
        this.briefing.status = 'FINISHED';
        break;
      case 'FINISHED':
        this.briefing.status = 'APPROVED';
        break;
      default:
        this.briefing.status = 'APPROVED'; // Estado padrão
    }

    // Atualizar a briefing no servidor
    this.servicoBriefing.editar(this.briefing)
      .subscribe(() => {});

    this.briefing = new Briefing();
  }

  // Método para cancelar edição
  cancelar(): void {

    this.briefing = new Briefing();

    this.btnCadastro = true;

    this.tabela = true;

    this.selecionarBriefings();

  }

  // Método para obter clientes filtrados
  getClientesFiltrados(): Cliente[] {
    return this.clientes.filter(c => c.id !== this.briefing.cliente?.id);
  }

  //Método de inicialização
  ngOnInit() {
    this.selecionarBriefings();
    this.selecionarClientes();
  }

}
