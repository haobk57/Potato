import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CustomerTypeComponent } from './customer-type.component';
import { CustomerTypeDetailComponent } from './customer-type-detail.component';
import { CustomerTypePopupComponent } from './customer-type-dialog.component';
import { CustomerTypeDeletePopupComponent } from './customer-type-delete-dialog.component';

@Injectable()
export class CustomerTypeResolvePagingParams implements Resolve<any> {

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

export const customerTypeRoute: Routes = [
    {
        path: 'customer-type',
        component: CustomerTypeComponent,
        resolve: {
            'pagingParams': CustomerTypeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_STAFF', 'ROLE_ADMIN'],
            pageTitle: 'putatuApp.customerType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customer-type/:id',
        component: CustomerTypeDetailComponent,
        data: {
            authorities: ['ROLE_STAFF', 'ROLE_ADMIN'],
            pageTitle: 'putatuApp.customerType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customerTypePopupRoute: Routes = [
    {
        path: 'customer-type-new',
        component: CustomerTypePopupComponent,
        data: {
            authorities: ['ROLE_STAFF', 'ROLE_ADMIN'],
            pageTitle: 'putatuApp.customerType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-type/:id/edit',
        component: CustomerTypePopupComponent,
        data: {
            authorities: ['ROLE_STAFF', 'ROLE_ADMIN'],
            pageTitle: 'putatuApp.customerType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-type/:id/delete',
        component: CustomerTypeDeletePopupComponent,
        data: {
            authorities: ['ROLE_STAFF', 'ROLE_ADMIN'],
            pageTitle: 'putatuApp.customerType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
