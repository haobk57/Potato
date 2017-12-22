import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../../shared';
import {
    ExchangeService,
    ExchangePopupService,
    ExchangeComponent,
    ExchangeDetailComponent,
    ExchangeDialogComponent,
    ExchangePopupComponent,
    ExchangeDeletePopupComponent,
    ExchangeDeleteDialogComponent,
    exchangeRoute,
    exchangePopupRoute,
    ExchangeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...exchangeRoute,
    ...exchangePopupRoute,
];

@NgModule({
    imports: [
        PutatuSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ExchangeComponent,
        ExchangeDetailComponent,
        ExchangeDialogComponent,
        ExchangeDeleteDialogComponent,
        ExchangePopupComponent,
        ExchangeDeletePopupComponent,
    ],
    entryComponents: [
        ExchangeComponent,
        ExchangeDialogComponent,
        ExchangePopupComponent,
        ExchangeDeleteDialogComponent,
        ExchangeDeletePopupComponent,
    ],
    providers: [
        ExchangeService,
        ExchangePopupService,
        ExchangeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuExchangeModule {}
