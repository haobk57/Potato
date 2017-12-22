import { BaseEntity } from './../../shared';

export class Product implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public percent?: number,
        public imageContentType?: string,
        public image?: any,
        public content?: string,
        public price?: number,
        public link?: string,
        public numberOfViews?: number,
        public createAt?: any,
        public category?,
        public provider?,
        public categoryId?,
        public providerId?,
    ) {
    }
}
