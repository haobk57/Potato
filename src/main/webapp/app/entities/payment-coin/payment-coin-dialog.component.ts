import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PaymentCoin } from './payment-coin.model';
import { PaymentCoinPopupService } from './payment-coin-popup.service';
import { PaymentCoinService } from './payment-coin.service';
import { Staff, StaffService } from '../staff';
import { Customer, CustomerService } from '../customer';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-payment-coin-dialog',
    templateUrl: './payment-coin-dialog.component.html'
})
export class PaymentCoinDialogComponent implements OnInit {

    paymentCoin: PaymentCoin;
    isSaving: boolean;

    staff: Staff[];

    customers: Customer[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private paymentCoinService: PaymentCoinService,
        private staffService: StaffService,
        private customerService: CustomerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.staffService.query()
            .subscribe((res: ResponseWrapper) => { this.staff = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.paymentCoin.id !== undefined) {
            this.subscribeToSaveResponse(
                this.paymentCoinService.update(this.paymentCoin));
        } else {
            this.subscribeToSaveResponse(
                this.paymentCoinService.create(this.paymentCoin));
        }
    }

    private subscribeToSaveResponse(result: Observable<PaymentCoin>) {
        result.subscribe((res: PaymentCoin) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: PaymentCoin) {
        this.eventManager.broadcast({ name: 'paymentCoinListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackStaffById(index: number, item: Staff) {
        return item.id;
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-payment-coin-popup',
    template: ''
})
export class PaymentCoinPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private paymentCoinPopupService: PaymentCoinPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.paymentCoinPopupService
                    .open(PaymentCoinDialogComponent as Component, params['id']);
            } else {
                this.paymentCoinPopupService
                    .open(PaymentCoinDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
