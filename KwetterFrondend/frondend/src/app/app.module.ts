import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from "@angular/forms";

import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {FrontpageComponent} from './frontpage/frontpage.component';
import {UserpageComponent} from './userpage/userpage.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {KweetComponent} from './kweet/kweet.component';
import {FrontpageTrendsComponent} from './frontpage/frontpage-trends/frontpage-trends.component';
import {FrontpageWhotofollowComponent} from './frontpage/frontpage-whotofollow/frontpage-whotofollow.component';
import {FrontendUserComponent} from './frontpage/frontend-user/frontend-user.component';
import {KweetAddComponent} from './kweet-add/kweet-add.component';
import {LoginComponent} from "./login/login.component";
import {AuthService} from "./services/auth.service";
import {AuthGuardService} from "./services/auth-guard.service";
import {UserpageEditComponent} from './userpage/userpage-edit/userpage-edit.component';
import {CookieService} from "ngx-cookie-service";
import {RegistrerComponent} from './registrer/registrer.component';


const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: FrontpageComponent},
  {path: 'kweet/:id', component: KweetComponent},
  {path: 'kweets/add', component: KweetAddComponent, canActivate: [AuthGuardService]},
  {path: 'user', component: UserpageComponent},
  {path: 'user/:username', component: UserpageComponent},
  {path: 'user/:username/edit', component: UserpageComponent, canActivate: [AuthGuardService]}, // TODO add user edit page
  {path: '**', component: FrontpageComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FrontpageComponent,
    UserpageComponent,
    HeaderComponent,
    FooterComponent,
    KweetComponent,
    FrontpageTrendsComponent,
    FrontpageWhotofollowComponent,
    FrontendUserComponent,
    KweetAddComponent,
    UserpageEditComponent,
    RegistrerComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    )
  ],
  providers: [AuthGuardService, AuthService, HttpClientModule, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
