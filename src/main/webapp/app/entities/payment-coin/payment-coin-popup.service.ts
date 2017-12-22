import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { PaymentCoin } from './payment-coin.model';
import { PaymentCoinService } from './payment-coin.service';

@Injectable()
export class PaymentCoinPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private paymentCoinService: PaymentCoinService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.paymentCoinService.find(id).subscribe((paymentCoin) => {
                    paymentCoin.createAt = this.datePipe
                        .transform(paymentCoin.createAt, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.paymentCoinModalRef(component, paymentCoin);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.paymentCoinModalRef(component, new PaymentCoin());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    paymentCoinModalRef(component: Component, paymentCoin: PaymentCoin): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.paymentCoin = paymentCoin;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
