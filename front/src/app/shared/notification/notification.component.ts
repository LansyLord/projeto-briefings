import { Component } from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent {
  isVisible: boolean = false; // Controla a visibilidade do alerta
  message: string = ''; // Mensagem do alerta
  notificationClass: string = 'alert-primary'; // Classe padrão
  private timeoutId: any; // Para limpar o timeout

  showNotification(message: string, type: 'success' | 'danger' | 'warning' | 'primary'): void {
    this.message = message;
    this.notificationClass = `alert-${type}`; // Define a classe do Bootstrap
    this.isVisible = true; // Exibe o alerta

    // Remove automaticamente após 5 segundos
    this.timeoutId = setTimeout(() => {
      this.closeNotification();
    }, 5000);
  }

  closeNotification(): void {
    this.isVisible = false; // Esconde o alerta
    clearTimeout(this.timeoutId); // Limpa o timeout para evitar problemas
  }
}
