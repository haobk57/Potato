import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PutatuSharedModule } from '../shared';

import { FAKE_ROUTES } from './fake.route';
import { FakeComponent } from './fake.component';
import { FakeProductComponent } from './fake-product.component';

@NgModule({
    imports: [
        PutatuSharedModule,
        RouterModule.forChild([ ...FAKE_ROUTES ])
    ],
    declarations: [
        FakeComponent,
        FakeProductComponent
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuFakeModule {}
