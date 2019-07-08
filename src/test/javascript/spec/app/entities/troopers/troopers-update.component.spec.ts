/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { TroopersUpdateComponent } from 'app/entities/troopers/troopers-update.component';
import { TroopersService } from 'app/entities/troopers/troopers.service';
import { Troopers } from 'app/shared/model/troopers.model';

describe('Component Tests', () => {
  describe('Troopers Management Update Component', () => {
    let comp: TroopersUpdateComponent;
    let fixture: ComponentFixture<TroopersUpdateComponent>;
    let service: TroopersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [TroopersUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TroopersUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TroopersUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TroopersService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Troopers(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Troopers();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
