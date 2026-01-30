import { Component } from '@angular/core';
import { GeneralServiceService } from '../../general-service.service';
import { Platforms } from '../../model/platforms.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private _generalService: GeneralServiceService) { }

  get platformsList(): Platforms[] {
    return this._generalService.platformsList;
  }
}
