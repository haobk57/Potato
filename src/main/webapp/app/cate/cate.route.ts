import { Routes } from '@angular/router';

import { CateComponent } from './';

export const CATE_ROUTE: Routes = [
    {
        path: 'cate',
        component: CateComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    }, {
        path: 'cate/:id',
        component: CateComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    }
];
