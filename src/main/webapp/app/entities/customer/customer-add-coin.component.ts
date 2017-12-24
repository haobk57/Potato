import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Customer } from './customer.model';
import { CustomerPopupService } from './customer-popup.service';
import { CustomerService } from './customer.service';
import { User, UserService } from '../../shared';
import { CustomerType, CustomerTypeService } from '../customer-type';
import { Account, LoginModalService, Principal, ResponseWrapper } from '../../shared';
import { Staff, StaffService } from './../staff';

@Component({
    selector: 'jhi-customer-add-coin',
    templateUrl: './customer-add-coin.component.html'
})
export class CustomerAddCoinComponent implements OnInit {

    customer: Customer;
    coin: number;
    isSaving: boolean;
    account: Account;
    staff: Staff;
    user: User;

    customertypes: CustomerType[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerService: CustomerService,
        private userService: UserService,
        private customerTypeService: CustomerTypeService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private staffService: StaffService
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then((account) => {
            this.account = account;
            this.loadUser();
        });
    }
    loadUser() {
        const login = this.account.login;
        this.userService.find(login).subscribe((user) => {
            this.user = user;
            this.loadStaff();
        });
    }
    loadStaff() {
        this.staffService.search({
            page: 0,
            query: 'user.id=' + this.user.id,
            size: 1,
            sort: ''}).subscribe(
                (res: ResponseWrapper) => { this.staff = res.json[0] }, null
            );
        return;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        console.log('customerId', this.customer.id);
        console.log('staff', this.staff.id);
        console.log('coin', this.coin);
        this.isSaving = true;
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerService.addCoin({
                    customerID: this.customer.id,
                    loginID: this.staff.id,
                    coin: this.coin
                }));
        } else {
        }
    }

    private subscribeToSaveResponse(result: Observable<any>) {
        result.subscribe((res: any) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: any) {
        this.eventManager.broadcast({ name: 'customerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackCustomerTypeById(index: number, item: CustomerType) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-add-coin-popup',
    template: ''
})
export class CustomerAddCoinPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPopupService: CustomerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerPopupService
                    .open(CustomerAddCoinComponent as Component, params['id']);
            } else {
                this.customerPopupService
                    .open(CustomerAddCoinComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
