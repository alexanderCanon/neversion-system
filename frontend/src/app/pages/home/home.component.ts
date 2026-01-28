import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  popularServices = [
    { name: 'Disney Plus', price: 40.00, logo: 'assets/Disney+_logo.svg' },
    { name: 'HBO Max', price: 30.00, logo: 'assets/HBO_Max_2024.svg' },
    { name: 'Prime Video', price: 30.00, logo: 'assets/primevideo_logo.svg' },
    { name: 'IPTV', price: 35.00, logo: 'assets/iptv.webp' },
    { name: 'Paramount Plus', price: 30.00, logo: 'assets/paramount_logo.svg' }
  ];
}
