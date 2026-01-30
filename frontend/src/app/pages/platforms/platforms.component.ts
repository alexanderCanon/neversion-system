import { Component } from '@angular/core';
import { GeneralServiceService } from '../../general-service.service';
import { Platforms } from '../../model/platforms.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-platforms',
  templateUrl: './platforms.component.html',
  styleUrls: ['./platforms.component.css'],
})
export class PlatformsComponent {
  constructor(private _generalService: GeneralServiceService, private _router: Router) { }

  get platformsList(): Platforms[] {
    return this._generalService.platformsList;
  }

  goToPlatformDetail(platform: Platforms) {
    this._router.navigate(['/platforms-detail', platform.id]);
  }
}
