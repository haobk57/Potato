import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../../shared';
import { PutatuAdminModule } from '../../admin/admin.module';
import {
    CustomerService,
    CustomerPopupService,
    CustomerComponent,
    CustomerDetailComponent,
    CustomerDialogComponent,
    CustomerPopupComponent,
    CustomerDeletePopupComponent,
    CustomerDeleteDialogComponent,
    customerRoute,
    customerPopupRoute,
    CustomerResolvePagingParams,
    CustomerAddCoinComponent,
    CustomerAddCoinPopupComponent
} from './';

const ENTITY_STATES = [
    ...customerRoute,
    ...customerPopupRoute,
];

@NgModule({
    imports: [
        PutatuSharedModule,
        PutatuAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CustomerComponent,
        CustomerDetailComponent,
        CustomerDialogComponent,
        CustomerDeleteDialogComponent,
        CustomerPopupComponent,
        CustomerDeletePopupComponent,
        CustomerAddCoinComponent,
        CustomerAddCoinPopupComponent
    ],
    entryComponents: [
        CustomerComponent,
        CustomerDialogComponent,
        CustomerPopupComponent,
        CustomerDeleteDialogComponent,
        CustomerDeletePopupComponent,
        CustomerAddCoinComponent,
        CustomerAddCoinPopupComponent
    ],
    providers: [
        CustomerService,
        CustomerPopupService,
        CustomerResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuCustomerModule {}
