import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { PaymentCoin } from './payment-coin.model';
import { PaymentCoinService } from './payment-coin.service';

@Component({
    selector: 'jhi-payment-coin-detail',
    templateUrl: './payment-coin-detail.component.html'
})
export class PaymentCoinDetailComponent implements OnInit, OnDestroy {

    paymentCoin: PaymentCoin;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private paymentCoinService: PaymentCoinService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPaymentCoins();
    }

    load(id) {
        this.paymentCoinService.find(id).subscribe((paymentCoin) => {
            this.paymentCoin = paymentCoin;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPaymentCoins() {
        this.eventSubscriber = this.eventManager.subscribe(
            'paymentCoinListModification',
            (response) => this.load(this.paymentCoin.id)
        );
    }
}
