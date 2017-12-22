import { Component, OnInit } from '@angular/core';
import { Category, CategoryService } from './../../entities/category';
import { Account, LoginModalService, Principal, ResponseWrapper } from '../../shared';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'jhi-menu',
    templateUrl: './menu.component.html',
    styleUrls: [
        'menu.scss'
    ]
})
export class MenuComponent implements OnInit {
    categories: Category[];
    searchString = '';

    constructor(
        private categoryService: CategoryService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.loadCategory();
        this.searchString = '';
    }

    loadCategory() {
        this.categoryService.query({
            page: 0,
            size: 10,
            sort: ''}).subscribe(
            (res: ResponseWrapper) => this.onLoadCategorySuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onLoadCategoryError(res.json)
        );
    }

    onLoadCategorySuccess(data, headers) {
        this.categories = data;
        console.log(this.categories);
    }
    onLoadCategoryError(error) {

    }
    changeRoute(id) {
        this.router.navigate(['/cate/', id]);
    }
}
