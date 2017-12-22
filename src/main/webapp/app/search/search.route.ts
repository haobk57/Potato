import { Routes } from '@angular/router';

import { SearchComponent } from './';

export const SEARCH_ROUTE: Routes = [
    {
        path: 'search',
        component: SearchComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    }, {
        path: 'search/:query',
        component: SearchComponent,
        data: {
            authorities: [],
            pageTitle: 'home.title'
        }
    }
];
