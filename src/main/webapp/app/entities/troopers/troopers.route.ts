import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Troopers } from 'app/shared/model/troopers.model';
import { TroopersService } from './troopers.service';
import { TroopersComponent } from './troopers.component';
import { TroopersDetailComponent } from './troopers-detail.component';
import { TroopersUpdateComponent } from './troopers-update.component';
import { TroopersDeletePopupComponent } from './troopers-delete-dialog.component';
import { ITroopers } from 'app/shared/model/troopers.model';

@Injectable({ providedIn: 'root' })
export class TroopersResolve implements Resolve<ITroopers> {
  constructor(private service: TroopersService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITroopers> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Troopers>) => response.ok),
        map((troopers: HttpResponse<Troopers>) => troopers.body)
      );
    }
    return of(new Troopers());
  }
}

export const troopersRoute: Routes = [
  {
    path: '',
    component: TroopersComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'App.troopers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TroopersDetailComponent,
    resolve: {
      troopers: TroopersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.troopers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TroopersUpdateComponent,
    resolve: {
      troopers: TroopersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.troopers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TroopersUpdateComponent,
    resolve: {
      troopers: TroopersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.troopers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const troopersPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TroopersDeletePopupComponent,
    resolve: {
      troopers: TroopersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.troopers.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
