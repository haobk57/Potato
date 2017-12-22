import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../../shared';
import {
    PaymentCoinService,
    PaymentCoinPopupService,
    PaymentCoinComponent,
    PaymentCoinDetailComponent,
    PaymentCoinDialogComponent,
    PaymentCoinPopupComponent,
    PaymentCoinDeletePopupComponent,
    PaymentCoinDeleteDialogComponent,
    paymentCoinRoute,
    paymentCoinPopupRoute,
    PaymentCoinResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...paymentCoinRoute,
    ...paymentCoinPopupRoute,
];

@NgModule({
    imports: [
        PutatuSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PaymentCoinComponent,
        PaymentCoinDetailComponent,
        PaymentCoinDialogComponent,
        PaymentCoinDeleteDialogComponent,
        PaymentCoinPopupComponent,
        PaymentCoinDeletePopupComponent,
    ],
    entryComponents: [
        PaymentCoinComponent,
        PaymentCoinDialogComponent,
        PaymentCoinPopupComponent,
        PaymentCoinDeleteDialogComponent,
        PaymentCoinDeletePopupComponent,
    ],
    providers: [
        PaymentCoinService,
        PaymentCoinPopupService,
        PaymentCoinResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuPaymentCoinModule {}
