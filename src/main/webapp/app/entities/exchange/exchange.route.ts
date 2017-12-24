import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ExchangeComponent } from './exchange.component';
import { ExchangeDetailComponent } from './exchange-detail.component';
import { ExchangePopupComponent } from './exchange-dialog.component';
import { ExchangeDeletePopupComponent } from './exchange-delete-dialog.component';

@Injectable()
export class ExchangeResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const exchangeRoute: Routes = [
    {
        path: 'exchange',
        component: ExchangeComponent,
        resolve: {
            'pagingParams': ExchangeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.exchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'exchange/:id',
        component: ExchangeDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.exchange.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const exchangePopupRoute: Routes = [
    {
        path: 'exchange-new',
        component: ExchangePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.exchange.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'exchange/:id/edit',
        component: ExchangePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.exchange.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'exchange/:id/delete',
        component: ExchangeDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.exchange.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
