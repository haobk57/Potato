import { BaseEntity } from './../../shared';

export class Provider implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public linkHome?: string,
        public createAt?: any,
        public imageContentType?: string,
        public image?: any,
        public percent?: number,
    ) {
    }
}
