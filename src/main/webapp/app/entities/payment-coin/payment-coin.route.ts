import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PaymentCoinComponent } from './payment-coin.component';
import { PaymentCoinDetailComponent } from './payment-coin-detail.component';
import { PaymentCoinPopupComponent } from './payment-coin-dialog.component';
import { PaymentCoinDeletePopupComponent } from './payment-coin-delete-dialog.component';

@Injectable()
export class PaymentCoinResolvePagingParams implements Resolve<any> {

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

export const paymentCoinRoute: Routes = [
    {
        path: 'payment-coin',
        component: PaymentCoinComponent,
        resolve: {
            'pagingParams': PaymentCoinResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.paymentCoin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'payment-coin/:id',
        component: PaymentCoinDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.paymentCoin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paymentCoinPopupRoute: Routes = [
    {
        path: 'payment-coin-new',
        component: PaymentCoinPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.paymentCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'payment-coin/:id/edit',
        component: PaymentCoinPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.paymentCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'payment-coin/:id/delete',
        component: PaymentCoinDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'putatuApp.paymentCoin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
