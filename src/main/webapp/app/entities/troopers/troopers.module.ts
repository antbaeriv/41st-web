import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { AppSharedModule } from 'app/shared';
import {
  TroopersComponent,
  TroopersDetailComponent,
  TroopersUpdateComponent,
  TroopersDeletePopupComponent,
  TroopersDeleteDialogComponent,
  troopersRoute,
  troopersPopupRoute
} from './';

const ENTITY_STATES = [...troopersRoute, ...troopersPopupRoute];

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TroopersComponent,
    TroopersDetailComponent,
    TroopersUpdateComponent,
    TroopersDeleteDialogComponent,
    TroopersDeletePopupComponent
  ],
  entryComponents: [TroopersComponent, TroopersUpdateComponent, TroopersDeleteDialogComponent, TroopersDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppTroopersModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
