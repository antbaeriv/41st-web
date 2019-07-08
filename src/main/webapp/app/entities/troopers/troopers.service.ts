import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITroopers } from 'app/shared/model/troopers.model';

type EntityResponseType = HttpResponse<ITroopers>;
type EntityArrayResponseType = HttpResponse<ITroopers[]>;

@Injectable({ providedIn: 'root' })
export class TroopersService {
  public resourceUrl = SERVER_API_URL + 'api/troopers';

  constructor(protected http: HttpClient) {}

  create(troopers: ITroopers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(troopers);
    return this.http
      .post<ITroopers>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(troopers: ITroopers): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(troopers);
    return this.http
      .put<ITroopers>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITroopers>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITroopers[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(troopers: ITroopers): ITroopers {
    const copy: ITroopers = Object.assign({}, troopers, {
      fechaEntradaServicio:
        troopers.fechaEntradaServicio != null && troopers.fechaEntradaServicio.isValid()
          ? troopers.fechaEntradaServicio.format(DATE_FORMAT)
          : null,
      fechaUltimaProm:
        troopers.fechaUltimaProm != null && troopers.fechaUltimaProm.isValid() ? troopers.fechaUltimaProm.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaEntradaServicio = res.body.fechaEntradaServicio != null ? moment(res.body.fechaEntradaServicio) : null;
      res.body.fechaUltimaProm = res.body.fechaUltimaProm != null ? moment(res.body.fechaUltimaProm) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((troopers: ITroopers) => {
        troopers.fechaEntradaServicio = troopers.fechaEntradaServicio != null ? moment(troopers.fechaEntradaServicio) : null;
        troopers.fechaUltimaProm = troopers.fechaUltimaProm != null ? moment(troopers.fechaUltimaProm) : null;
      });
    }
    return res;
  }
}
