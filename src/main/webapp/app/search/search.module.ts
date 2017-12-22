import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../shared';

import { SEARCH_ROUTE, SearchComponent } from './';

@NgModule({
    imports: [
        PutatuSharedModule,
        RouterModule.forChild([ ...SEARCH_ROUTE ])
    ],
    declarations: [
        SearchComponent
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuSearchModule {}
