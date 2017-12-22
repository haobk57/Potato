import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { Account, LoginModalService, Principal, ResponseWrapper } from '../shared';
import { CategoryService } from '../entities/category/category.service';
import { Category } from '../entities/category/category.model';
import { User, UserService } from '../shared';
import { Customer, CustomerService } from './../entities/customer';
import { Product, ProductService } from '../entities/product';

@Component({
    selector: 'jhi-cate',
    templateUrl: './cate.component.html',
    styleUrls: [
        'cate.scss'
    ]

})
export class CateComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    category: Category;
    products: Product[];
    customer: Customer;
    user: User;
    id = 0;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private categoryService: CategoryService,
        private productService: ProductService,
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
        const id = url.split('/')[2];
        this.loadCategory(id);
        this.loadProducts(id);
    }
    loadCategory(id) {
        this.categoryService.find(id).subscribe((category) => {
            this.category = category;
        });
    }
    loadProducts(id) {
        this.productService.search({
        page: 0,
        query: 'category.id=' + id,
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
                window.open('http://localhost:8080/#/fake-product?productID=' + this.id + '&key=potato&customerID='+ this.customer.id);
            }, null
            );
        return;
    }
}
