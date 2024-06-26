import { Cliente } from "./Cliente";

export class Briefing{


  id: number = 0;
  cliente: Cliente | null = null; // Definindo cliente como null por padrão
  clienteId: number | null = this.cliente?.id ?? null; // Definindo clienteId como null por padrão
  clienteName: string = this.cliente?.name ?? ''; // Definindo clienteName como string vazia por padrão
  task: string = '';
  description: string = '';
  status: string = 'APPROVED';
  date: Date = new Date();

  constructor() {
      // Definindo valores padrão para clienteId e clienteName no construtor
      this.clienteId = this.cliente?.id ?? null;
      this.clienteName = this.cliente?.name ?? '';
  }
}

