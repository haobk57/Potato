import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PaymentCoin } from './payment-coin.model';
import { PaymentCoinPopupService } from './payment-coin-popup.service';
import { PaymentCoinService } from './payment-coin.service';

@Component({
    selector: 'jhi-payment-coin-delete-dialog',
    templateUrl: './payment-coin-delete-dialog.component.html'
})
export class PaymentCoinDeleteDialogComponent {

    paymentCoin: PaymentCoin;

    constructor(
        private paymentCoinService: PaymentCoinService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.paymentCoinService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'paymentCoinListModification',
                content: 'Deleted an paymentCoin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-payment-coin-delete-popup',
    template: ''
})
export class PaymentCoinDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private paymentCoinPopupService: PaymentCoinPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.paymentCoinPopupService
                .open(PaymentCoinDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
