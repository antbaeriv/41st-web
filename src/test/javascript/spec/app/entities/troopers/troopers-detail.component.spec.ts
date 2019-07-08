/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { TroopersDetailComponent } from 'app/entities/troopers/troopers-detail.component';
import { Troopers } from 'app/shared/model/troopers.model';

describe('Component Tests', () => {
  describe('Troopers Management Detail Component', () => {
    let comp: TroopersDetailComponent;
    let fixture: ComponentFixture<TroopersDetailComponent>;
    const route = ({ data: of({ troopers: new Troopers(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [TroopersDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TroopersDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TroopersDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.troopers).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
