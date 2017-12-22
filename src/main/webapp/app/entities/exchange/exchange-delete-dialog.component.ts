import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Exchange } from './exchange.model';
import { ExchangePopupService } from './exchange-popup.service';
import { ExchangeService } from './exchange.service';

@Component({
    selector: 'jhi-exchange-delete-dialog',
    templateUrl: './exchange-delete-dialog.component.html'
})
export class ExchangeDeleteDialogComponent {

    exchange: Exchange;

    constructor(
        private exchangeService: ExchangeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.exchangeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'exchangeListModification',
                content: 'Deleted an exchange'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-exchange-delete-popup',
    template: ''
})
export class ExchangeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private exchangePopupService: ExchangePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.exchangePopupService
                .open(ExchangeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
