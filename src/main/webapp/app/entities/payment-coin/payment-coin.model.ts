import { BaseEntity } from './../../shared';

export class PaymentCoin implements BaseEntity {
    constructor(
        public id?: number,
        public method?: string,
        public name?: string,
        public coin?: number,
        public money?: number,
        public createAt?: any,
        public staff?,
        public customer?,
        public staffId?,
        public customerId?,
    ) {
    }
}
