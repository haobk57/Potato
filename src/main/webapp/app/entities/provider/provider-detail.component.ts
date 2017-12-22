import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Provider } from './provider.model';
import { ProviderService } from './provider.service';

@Component({
    selector: 'jhi-provider-detail',
    templateUrl: './provider-detail.component.html'
})
export class ProviderDetailComponent implements OnInit, OnDestroy {

    provider: Provider;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private providerService: ProviderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProviders();
    }

    load(id) {
        this.providerService.find(id).subscribe((provider) => {
            this.provider = provider;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProviders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'providerListModification',
            (response) => this.load(this.provider.id)
        );
    }
}
