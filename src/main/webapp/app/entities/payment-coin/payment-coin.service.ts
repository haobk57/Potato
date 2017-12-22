import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { PaymentCoin } from './payment-coin.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PaymentCoinService {

    private resourceUrl = SERVER_API_URL + 'api/payment-coins';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/payment-coins';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(paymentCoin: PaymentCoin): Observable<PaymentCoin> {
        const copy = this.convert(paymentCoin);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(paymentCoin: PaymentCoin): Observable<PaymentCoin> {
        const copy = this.convert(paymentCoin);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<PaymentCoin> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to PaymentCoin.
     */
    private convertItemFromServer(json: any): PaymentCoin {
        const entity: PaymentCoin = Object.assign(new PaymentCoin(), json);
        entity.createAt = this.dateUtils
            .convertDateTimeFromServer(json.createAt);
        return entity;
    }

    /**
     * Convert a PaymentCoin to a JSON which can be sent to the server.
     */
    private convert(paymentCoin: PaymentCoin): PaymentCoin {
        const copy: PaymentCoin = Object.assign({}, paymentCoin);

        copy.createAt = this.dateUtils.toDate(paymentCoin.createAt);
        return copy;
    }
}
