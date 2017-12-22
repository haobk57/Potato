import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../../shared';
import { PutatuAdminModule } from '../../admin/admin.module';
import {
    StaffService,
    StaffPopupService,
    StaffComponent,
    StaffDetailComponent,
    StaffDialogComponent,
    StaffPopupComponent,
    StaffDeletePopupComponent,
    StaffDeleteDialogComponent,
    staffRoute,
    staffPopupRoute,
    StaffResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...staffRoute,
    ...staffPopupRoute,
];

@NgModule({
    imports: [
        PutatuSharedModule,
        PutatuAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StaffComponent,
        StaffDetailComponent,
        StaffDialogComponent,
        StaffDeleteDialogComponent,
        StaffPopupComponent,
        StaffDeletePopupComponent,
    ],
    entryComponents: [
        StaffComponent,
        StaffDialogComponent,
        StaffPopupComponent,
        StaffDeleteDialogComponent,
        StaffDeletePopupComponent,
    ],
    providers: [
        StaffService,
        StaffPopupService,
        StaffResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuStaffModule {}
