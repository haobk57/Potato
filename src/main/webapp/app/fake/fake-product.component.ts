import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Provider, ProviderService } from './../entities/provider';
import { Product, ProductService } from './../entities/product';
import { Exchange, ExchangeService } from './../entities/exchange';

@Component({
    selector: 'jhi-fake-product',
    templateUrl: './fake-product.component.html',
    styleUrls: [
        'fake-product.scss'
    ]

})
export class FakeProductComponent implements OnInit {
    provider: Provider;
    product: Product;
    exchange;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private providerService: ProviderService,
        private productService: ProductService,
        private exchangeService: ExchangeService
    ) {
    }

    ngOnInit() {
        this.route.queryParams.subscribe( (params) => {
            const id = params['productID'];
            const customerID = params['customerID'];
            this.exchange = {};
            this.exchange.productId = id;
            this.exchange.exchangeId = customerID;
            this.loadProduct(id);
        });
    }
    loadProduct(id) {
        this.productService.find(id).subscribe((product) => {
            this.product = product;
            this.exchange.coin = Math.round(this.product.price * this.product.percent / 100);
        });
    }
    buy() {
        console.log('Mua Hàng');
        this.exchangeService.create(this.exchange)
        .subscribe((res) => {alert('Mua thành công')}, null);
    }
}
