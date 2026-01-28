import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { ServiceCardComponent } from './components/service-card/service-card.component';
import { FloatingWhatsappComponent } from './components/floating-whatsapp/floating-whatsapp.component';
import { PaymentMethodsComponent } from './pages/payment-methods/payment-methods.component';
import { OffersComponent } from './offers/offers.component';
import { ComboComponent } from './combo/combo.component';
import { PlatformsComponent } from './platforms/platforms.component';
import { GamesComponent } from './games/games.component';
import { WholesalersComponent } from './wholesalers/wholesalers.component';
import { AdminComponent } from './admin/admin.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { PaymentPageComponent } from './payment-page/payment-page.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    FooterComponent,
    HomeComponent,
    ServiceCardComponent,
    FloatingWhatsappComponent,
    PaymentMethodsComponent,
    OffersComponent,
    ComboComponent,
    PlatformsComponent,
    GamesComponent,
    WholesalersComponent,
    AdminComponent,
    CheckoutComponent,
    PaymentPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
