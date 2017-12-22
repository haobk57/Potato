import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../shared';

import { CATE_ROUTE, CateComponent } from './';

@NgModule({
    imports: [
        PutatuSharedModule,
        RouterModule.forChild([ ...CATE_ROUTE ])
    ],
    declarations: [
        CateComponent
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuCateModule {}
