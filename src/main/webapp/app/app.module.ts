import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { PutatuSharedModule, UserRouteAccessService } from './shared';
import { PutatuAppRoutingModule} from './app-routing.module';
import { PutatuHomeModule } from './home/home.module';
import { PutatuAdminModule } from './admin/admin.module';
import { PutatuAccountModule } from './account/account.module';
import { PutatuEntityModule } from './entities/entity.module';
import { PutatuCateModule } from './cate/cate.module';
import { PutatuSearchModule } from './search/search.module';
import { PutatuFakeModule } from './fake/fake.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        PutatuAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        PutatuSharedModule,
        PutatuHomeModule,
        PutatuAdminModule,
        PutatuAccountModule,
        PutatuEntityModule,
        PutatuCateModule,
        PutatuSearchModule,
        PutatuFakeModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class PutatuAppModule {}
