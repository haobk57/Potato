import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Exchange } from './exchange.model';
import { ExchangePopupService } from './exchange-popup.service';
import { ExchangeService } from './exchange.service';
import { Customer, CustomerService } from '../customer';
import { Product, ProductService } from '../product';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-exchange-dialog',
    templateUrl: './exchange-dialog.component.html'
})
export class ExchangeDialogComponent implements OnInit {

    exchange: Exchange;
    isSaving: boolean;

    customers: Customer[];

    products: Product[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private exchangeService: ExchangeService,
        private customerService: CustomerService,
        private productService: ProductService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.customerService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.productService.query()
            .subscribe((res: ResponseWrapper) => { this.products = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.exchange.id !== undefined) {
            this.subscribeToSaveResponse(
                this.exchangeService.update(this.exchange));
        } else {
            this.subscribeToSaveResponse(
                this.exchangeService.create(this.exchange));
        }
    }

    private subscribeToSaveResponse(result: Observable<Exchange>) {
        result.subscribe((res: Exchange) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Exchange) {
        this.eventManager.broadcast({ name: 'exchangeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackProductById(index: number, item: Product) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-exchange-popup',
    template: ''
})
export class ExchangePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private exchangePopupService: ExchangePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.exchangePopupService
                    .open(ExchangeDialogComponent as Component, params['id']);
            } else {
                this.exchangePopupService
                    .open(ExchangeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
