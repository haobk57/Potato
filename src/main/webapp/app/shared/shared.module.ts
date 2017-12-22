import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DatePipe } from '@angular/common';
import {
    PutatuSharedLibsModule,
    PutatuSharedCommonModule,
    CSRFService,
    AuthServerProvider,
    AccountService,
    UserService,
    StateStorageService,
    LoginService,
    LoginModalService,
    JhiLoginModalComponent,
    Principal,
    HasAnyAuthorityDirective,
    JhiSocialComponent,
    SocialService
} from './';

import { RouterModule } from '@angular/router';

import {MenuComponent} from './menu/menu.component';

@NgModule({
    imports: [
        PutatuSharedLibsModule,
        PutatuSharedCommonModule,
        RouterModule
    ],
    declarations: [
        JhiSocialComponent,
        JhiLoginModalComponent,
        HasAnyAuthorityDirective,
        MenuComponent
    ],
    providers: [
        LoginService,
        LoginModalService,
        AccountService,
        StateStorageService,
        Principal,
        CSRFService,
        AuthServerProvider,
        SocialService,
        UserService,
        DatePipe
    ],
    entryComponents: [JhiLoginModalComponent],
    exports: [
        PutatuSharedCommonModule,
        JhiSocialComponent,
        JhiLoginModalComponent,
        HasAnyAuthorityDirective,
        DatePipe,
        MenuComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class PutatuSharedModule {}
