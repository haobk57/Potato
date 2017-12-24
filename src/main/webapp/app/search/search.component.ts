import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { Product, ProductService } from '../entities/product';
import { User, UserService } from '../shared';
import { Customer, CustomerService } from './../entities/customer';
import { Account, LoginModalService, Principal, ResponseWrapper } from '../shared';

@Component({
    selector: 'jhi-search',
    templateUrl: './search.component.html',
    styleUrls: [
        'search.scss'
    ]

})
export class SearchComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    products: Product[];
    customer: Customer;
    user: User;
    id = 0;
    constructor(
        private productService: ProductService,
        private router: Router,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private customerService: CustomerService,
        private userService: UserService
    ) {
    }

    ngOnInit() {
        this.loadAll();
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.loadAll();
            }
        });
    }
    loadAll() {
        const url = this.router.url;
        const query = url.split('/')[2];
        this.loadProducts(query);
    }
    loadProducts(query) {
        this.productService.search({
        page: 0,
        query,
        size: 20,
        sort: ''}).subscribe(
            (res) => {this.products = res.json},
            null
        );
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
                window.open('http://localhost:8080/#/fake-product?productID=' + this.id + '&key=potato&customerID=' + this.customer.id);
            }, null
            );
        return;
    }
}
