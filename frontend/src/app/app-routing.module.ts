import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PaymentMethodsComponent } from './pages/payment-methods/payment-methods.component';
import { OffersComponent } from './pages/offers/offers.component';
import { GamesComponent } from './pages/games/games.component';
import { PlatformsComponent } from './pages/platforms/platforms.component';
import { ComboComponent } from './pages/combo/combo.component';
import { WholesalersComponent } from './pages/wholesalers/wholesalers.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { AdminComponent } from './pages/admin/admin.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'payment-methods', component: PaymentMethodsComponent },
  { path: 'offers', component: OffersComponent },
  { path: 'games', component: GamesComponent },
  { path: 'platforms', component: PlatformsComponent },
  { path: 'combo', component: ComboComponent },
  { path: 'wholesalers', component: WholesalersComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'admin', component: AdminComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
