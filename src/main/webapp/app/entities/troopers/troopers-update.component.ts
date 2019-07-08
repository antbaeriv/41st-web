import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ITroopers, Troopers } from 'app/shared/model/troopers.model';
import { TroopersService } from './troopers.service';

@Component({
  selector: 'jhi-troopers-update',
  templateUrl: './troopers-update.component.html'
})
export class TroopersUpdateComponent implements OnInit {
  troopers: ITroopers;
  isSaving: boolean;
  fechaEntradaServicioDp: any;
  fechaUltimaPromDp: any;

  editForm = this.fb.group({
    id: [],
    numero: [],
    rango: [],
    apodo: [],
    isactive: [],
    amonestacion: [],
    rol: [],
    fechaEntradaServicio: [],
    fechaUltimaProm: [],
    reclutador: []
  });

  constructor(protected troopersService: TroopersService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ troopers }) => {
      this.updateForm(troopers);
      this.troopers = troopers;
    });
  }

  updateForm(troopers: ITroopers) {
    this.editForm.patchValue({
      id: troopers.id,
      numero: troopers.numero,
      rango: troopers.rango,
      apodo: troopers.apodo,
      isactive: troopers.isactive,
      amonestacion: troopers.amonestacion,
      rol: troopers.rol,
      fechaEntradaServicio: troopers.fechaEntradaServicio,
      fechaUltimaProm: troopers.fechaUltimaProm,
      reclutador: troopers.reclutador
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const troopers = this.createFromForm();
    if (troopers.id !== undefined) {
      this.subscribeToSaveResponse(this.troopersService.update(troopers));
    } else {
      this.subscribeToSaveResponse(this.troopersService.create(troopers));
    }
  }

  private createFromForm(): ITroopers {
    const entity = {
      ...new Troopers(),
      id: this.editForm.get(['id']).value,
      numero: this.editForm.get(['numero']).value,
      rango: this.editForm.get(['rango']).value,
      apodo: this.editForm.get(['apodo']).value,
      isactive: this.editForm.get(['isactive']).value,
      amonestacion: this.editForm.get(['amonestacion']).value,
      rol: this.editForm.get(['rol']).value,
      fechaEntradaServicio: this.editForm.get(['fechaEntradaServicio']).value,
      fechaUltimaProm: this.editForm.get(['fechaUltimaProm']).value,
      reclutador: this.editForm.get(['reclutador']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITroopers>>) {
    result.subscribe((res: HttpResponse<ITroopers>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
