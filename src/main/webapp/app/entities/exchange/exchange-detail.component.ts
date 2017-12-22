import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Exchange } from './exchange.model';
import { ExchangeService } from './exchange.service';

@Component({
    selector: 'jhi-exchange-detail',
    templateUrl: './exchange-detail.component.html'
})
export class ExchangeDetailComponent implements OnInit, OnDestroy {

    exchange: Exchange;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private exchangeService: ExchangeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInExchanges();
    }

    load(id) {
        this.exchangeService.find(id).subscribe((exchange) => {
            this.exchange = exchange;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInExchanges() {
        this.eventSubscriber = this.eventManager.subscribe(
            'exchangeListModification',
            (response) => this.load(this.exchange.id)
        );
    }
}
