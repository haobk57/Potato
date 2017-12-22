import { BaseEntity } from './../../shared';

export class Customer implements BaseEntity {
    constructor(
        public id?: number,
        public coin?: number,
        public user?,
        public type?,
        public userId?,
        public typeId?,
    ) {
    }
}
