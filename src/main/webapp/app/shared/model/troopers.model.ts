import { Moment } from 'moment';

export interface ITroopers {
  id?: number;
  numero?: string;
  rango?: string;
  apodo?: string;
  isactive?: number;
  amonestacion?: number;
  rol?: string;
  fechaEntradaServicio?: Moment;
  fechaUltimaProm?: Moment;
  reclutador?: string;
}

export class Troopers implements ITroopers {
  constructor(
    public id?: number,
    public numero?: string,
    public rango?: string,
    public apodo?: string,
    public isactive?: number,
    public amonestacion?: number,
    public rol?: string,
    public fechaEntradaServicio?: Moment,
    public fechaUltimaProm?: Moment,
    public reclutador?: string
  ) {}
}
