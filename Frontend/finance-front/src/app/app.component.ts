import { Component, HostListener, OnInit, signal } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  menuOpen = false;
  mobileOpen = false;
  notificationCount = 3;

  userName = 'María García';
  userEmail = 'maria@email.com';
  userInitials = 'MG';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    // Cargar datos del usuario desde authService
    // const user = this.authService.currentUser();
    // this.userName = user.name;
    // this.userEmail = user.email;
    // this.userInitials = user.name.split(' ').map(n => n[0]).join('').toUpperCase();
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  toggleMobile(): void {
    this.mobileOpen = !this.mobileOpen;
  }

  toggleNotifications(): void {
    // abrir panel de notificaciones
    console.log('Notificaciones');
  }

  onLogout(): void {
    this.menuOpen = false;
    this.mobileOpen = false;
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  // Cerrar dropdown al hacer click fuera
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    const target = event.target as HTMLElement;
    if (!target.closest('.user-menu')) {
      this.menuOpen = false;
    }
    if (!target.closest('.hamburger') && !target.closest('.mobile-nav')) {
      this.mobileOpen = false;
    }
  }
}
