import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Exchange } from './exchange.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ExchangeService {

    private resourceUrl = SERVER_API_URL + 'api/exchanges';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/exchanges';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(exchange: Exchange): Observable<Exchange> {
        const copy = this.convert(exchange);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(exchange: Exchange): Observable<Exchange> {
        const copy = this.convert(exchange);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Exchange> {
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
     * Convert a returned JSON object to Exchange.
     */
    private convertItemFromServer(json: any): Exchange {
        const entity: Exchange = Object.assign(new Exchange(), json);
        entity.createAt = this.dateUtils
            .convertDateTimeFromServer(json.createAt);
        return entity;
    }

    /**
     * Convert a Exchange to a JSON which can be sent to the server.
     */
    private convert(exchange: Exchange): Exchange {
        const copy: Exchange = Object.assign({}, exchange);

        copy.createAt = this.dateUtils.toDate(exchange.createAt);
        return copy;
    }
}
