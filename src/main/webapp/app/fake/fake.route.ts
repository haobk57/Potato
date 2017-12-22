import { Routes } from '@angular/router';

import { FakeComponent } from './fake.component';
import { FakeProductComponent } from './fake-product.component';

export const FAKE_ROUTES: Routes = [
    {
        path: 'fake/:page',
        component: FakeComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    },
    {
        path: 'fake-product',
        component: FakeProductComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    }
];
