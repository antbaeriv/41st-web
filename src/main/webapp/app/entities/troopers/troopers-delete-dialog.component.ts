import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITroopers } from 'app/shared/model/troopers.model';
import { TroopersService } from './troopers.service';

@Component({
  selector: 'jhi-troopers-delete-dialog',
  templateUrl: './troopers-delete-dialog.component.html'
})
export class TroopersDeleteDialogComponent {
  troopers: ITroopers;

  constructor(protected troopersService: TroopersService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.troopersService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'troopersListModification',
        content: 'Deleted an troopers'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-troopers-delete-popup',
  template: ''
})
export class TroopersDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ troopers }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TroopersDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.troopers = troopers;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/troopers', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/troopers', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
