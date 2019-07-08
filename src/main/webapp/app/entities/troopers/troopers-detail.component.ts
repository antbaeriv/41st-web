import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITroopers } from 'app/shared/model/troopers.model';

@Component({
  selector: 'jhi-troopers-detail',
  templateUrl: './troopers-detail.component.html'
})
export class TroopersDetailComponent implements OnInit {
  troopers: ITroopers;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ troopers }) => {
      this.troopers = troopers;
    });
  }

  previousState() {
    window.history.back();
  }
}
