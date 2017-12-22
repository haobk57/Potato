import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PutatuStaffModule } from './staff/staff.module';
import { PutatuPositionModule } from './position/position.module';
import { PutatuCustomerModule } from './customer/customer.module';
import { PutatuCustomerTypeModule } from './customer-type/customer-type.module';
import { PutatuPaymentCoinModule } from './payment-coin/payment-coin.module';
import { PutatuExchangeModule } from './exchange/exchange.module';
import { PutatuProductModule } from './product/product.module';
import { PutatuProviderModule } from './provider/provider.module';
import { PutatuCategoryModule } from './category/category.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PutatuStaffModule,
        PutatuPositionModule,
        PutatuCustomerModule,
        PutatuCustomerTypeModule,
        PutatuPaymentCoinModule,
        PutatuExchangeModule,
        PutatuProductModule,
        PutatuProviderModule,
        PutatuCategoryModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PutatuEntityModule {}
