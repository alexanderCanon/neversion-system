import { Injectable } from '@angular/core';
import { Platforms } from './model/platforms.model';

@Injectable({
  providedIn: 'root'
})


export class GeneralServiceService {
  private _platformsList: Platforms[] = [
    {
      id: 1,
      name: 'Disney Plus',
      description: 'Plataforma de streaming con contenido de Disney, Pixar, Marvel, Star Wars y National Geographic.',
      price: 40.00,
      logo: '../../assets/Disney+_logo.svg'
    },
    {
      id: 2,
      name: 'HBO Max',
      description: 'Plataforma de streaming con contenido de HBO, Warner Bros., DC Comics, Cartoon Network y Discovery.',
      price: 30.00,
      logo: '../../assets/HBO_Max_2024.svg'
    },
    {
      id: 3,
      name: 'Prime Video',
      description: 'Plataforma de streaming con contenido de Amazon Prime Video, incluye películas, series y documentales.',
      price: 30.00,
      logo: '../../assets/primevideo_logo.svg'
    },
    {
      id: 4,
      name: 'IPTV',
      description: 'Plataforma de streaming con contenido de canales de televisión en vivo.',
      price: 35.00,
      logo: '../../assets/iptv.webp'
    },
    {
      id: 5,
      name: 'Paramount Plus',
      description: 'Plataforma de streaming con contenido de Paramount Pictures, CBS, Showtime, MTV, Comedy Central y Nickelodeon.',
      price: 30.00,
      logo: '../../assets/paramount_logo.svg'
    }
  ];

  get platformsList(): Platforms[] {
    return this._platformsList;
  }
  constructor() { }
}
