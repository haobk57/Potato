import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal, ResponseWrapper } from '../shared';
import { CategoryService } from '../entities/category/category.service';
import { Category } from '../entities/category/category.model';

import { ProviderService } from '../entities/provider/provider.service';
import { Provider } from '../entities/provider/provider.model';
import { ProductService } from '../entities/product/product.service';
import { Product } from '../entities/product/product.model';
import { User, UserService } from '../shared';
import { Customer, CustomerService } from './../entities/customer';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    providers: Provider[];
    products: Product[];
    newProducts: Product[];
    customer: Customer;
    user: User;
    id = 0;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private providerService: ProviderService,
        private productService: ProductService,
        private customerService: CustomerService,
        private userService: UserService
    ) {
    }

    ngOnInit() {
        this.loadNewProduct();
        this.loadProvider();
        this.loadProduct();
        
    }
    
    loadProvider() {
        this.providerService.query({
            page: 0,
            size: 8,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => this.onLoadProviderSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadProviderError(res.json)
        );
    }

    loadNewProduct() {
        this.productService.query({
            page: 0,
            size: 4,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => { this.newProducts = res.json}, null
        );
    }
    loadProduct() {
        this.productService.query({
            page: 0,
            size: 8,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => this.onLoadProductSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadProductError(res.json)
        );
    }
    onLoadProductSuccess(data, headers) {
        this.products = data;
    }
    onLoadProductError(error) {

    }
    private onLoadProviderSuccess(data, headers) {
        this.providers = data;
    }
    private onLoadProviderError(error) {
    }
    redirect(id) {
        this.id = id;
        this.principal.identity().then((account) => {
            this.account = account;
            this.loadUser();
        });
    }
    loadUser() {
        const login = this.account.login;
        this.userService.find(login).subscribe((user) => {
            this.user = user;
            this.loadCustomer();
        });
    }
    loadCustomer() {
        this.customerService.search({
            page: 0,
            query: 'user.id=' + this.user.id,
            size: 1,
            sort: ''}).subscribe(
                (res: ResponseWrapper) => { this.customer = res.json[0];
                window.open('http://localhost:8080/#/fake-product?productID=' + this.id + '&key=potato&customerID='+ this.customer.id);
            }, null
            );
        return;
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
