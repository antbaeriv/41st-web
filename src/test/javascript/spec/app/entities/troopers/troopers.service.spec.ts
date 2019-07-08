/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TroopersService } from 'app/entities/troopers/troopers.service';
import { ITroopers, Troopers } from 'app/shared/model/troopers.model';

describe('Service Tests', () => {
  describe('Troopers Service', () => {
    let injector: TestBed;
    let service: TroopersService;
    let httpMock: HttpTestingController;
    let elemDefault: ITroopers;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(TroopersService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Troopers(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fechaEntradaServicio: currentDate.format(DATE_FORMAT),
            fechaUltimaProm: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Troopers', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaEntradaServicio: currentDate.format(DATE_FORMAT),
            fechaUltimaProm: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaEntradaServicio: currentDate,
            fechaUltimaProm: currentDate
          },
          returnedFromService
        );
        service
          .create(new Troopers(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Troopers', async () => {
        const returnedFromService = Object.assign(
          {
            numero: 'BBBBBB',
            rango: 'BBBBBB',
            apodo: 'BBBBBB',
            isactive: 1,
            amonestacion: 1,
            rol: 'BBBBBB',
            fechaEntradaServicio: currentDate.format(DATE_FORMAT),
            fechaUltimaProm: currentDate.format(DATE_FORMAT),
            reclutador: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaEntradaServicio: currentDate,
            fechaUltimaProm: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Troopers', async () => {
        const returnedFromService = Object.assign(
          {
            numero: 'BBBBBB',
            rango: 'BBBBBB',
            apodo: 'BBBBBB',
            isactive: 1,
            amonestacion: 1,
            rol: 'BBBBBB',
            fechaEntradaServicio: currentDate.format(DATE_FORMAT),
            fechaUltimaProm: currentDate.format(DATE_FORMAT),
            reclutador: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaEntradaServicio: currentDate,
            fechaUltimaProm: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Troopers', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
