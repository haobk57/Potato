import { BaseEntity } from './../../shared';

export class Staff implements BaseEntity {
    constructor(
        public id?: number,
        public salary?: number,
        public user?,
        public position?,
        public userId?,
        public positionId?,
    ) {
    }
}
