import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Provider, ProviderService } from './../entities/provider';

@Component({
    selector: 'jhi-fake',
    templateUrl: './fake.component.html',
    styleUrls: [
        'fake.scss'
    ]

})
export class FakeComponent implements OnInit {
    provider: Provider;
    constructor(
        private router: Router,
        private providerService: ProviderService
    ) {
    }

    ngOnInit() {
        const url = this.router.url;
        const providerName = url.split('/')[2];
        this.findProviderByName(providerName);
    }

    findProviderByName(name) {
        this.providerService.search({
        page: 0,
        query: name,
        size: 1,
        sort: ''}).subscribe(
            (res) => {this.provider = res.json[0]},
            null
        );
    }

}
