import { BaseEntity } from './../../shared';

export class Exchange implements BaseEntity {
    constructor(
        public id?: number,
        public coin?: number,
        public createAt?: any,
        public exchange?,
        public product?,
        public exchangeId?,
        public productId?,
    ) {
    }
}
